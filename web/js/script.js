'use strict';

function loadCoupons() {

    //AJAX
    var xhttp = new XMLHttpRequest();

    /*
    onreadystatechange.readyState
        0: request not initialized
        1: server connection established
        2: request received
        3: processing request
        4: request finished and response is ready
     */

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var result = JSON.parse(this.response);

            // Dohvati tabelu iz DOM-a po id-u
            var table = document.getElementById("coupon-tbl");

            // Dohvati prvi tbody
            var oldTBody = table.tBodies[0];
            var newTBody = document.createElement("tBody");

            for (var i = 0; i < result.length; i++) {

                var bRow = document.createElement("tr"); // Kreiraj novi red

                // Postavi vrednosti za taj red iz rezultata sa servera
                var tdProductName = document.createElement("td");
                var tdStoreName = document.createElement("td");
                var tdPriceWithDiscount = document.createElement("td");
                var tdPriceWithoutDiscount = document.createElement("td");
                var tdDiscountPercent = document.createElement("td");
                var tdDelete = document.createElement("td");


                var discountedPrice = result[i].discountedPrice;
                var originalPrice = result[i].originalPrice;

                var discount = ((originalPrice - discountedPrice) / (originalPrice)) * 100;

                tdProductName.innerHTML = result[i].productName;
                tdStoreName.innerHTML = result[i].shop.shopName;
                tdPriceWithDiscount.innerHTML = discountedPrice;
                tdPriceWithoutDiscount.innerHTML = originalPrice;
                tdDiscountPercent.innerHTML = discount;

                var deleteBtn = document.createElement('input');
                deleteBtn.onclick = (function (result, i) {
                    return function () {
                        deleteCoupon(result[i].id);
                    }
                })(result, i);
                deleteBtn.type = "button";
                deleteBtn.value = "Delete";
                tdDelete.appendChild(deleteBtn);

                bRow.appendChild(tdProductName);
                bRow.appendChild(tdStoreName);
                bRow.appendChild(tdPriceWithDiscount);
                bRow.appendChild(tdPriceWithoutDiscount);
                bRow.appendChild(tdDiscountPercent);
                bRow.appendChild(tdDelete);

                newTBody.appendChild(bRow)
            }

            // Zameni postojeci sa novim tBody-jem.
            // Na taj nacin ce uvek da se ispisuju svezi elementi a stari da nestanu.
            table.replaceChild(newTBody, oldTBody)
        }
    };
    xhttp.open("GET", "/coupon/coupons", true);
    xhttp.send();
}

function deleteCoupon(id) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 204) {
            loadCoupons();
        }
    };
    xhttp.open("DELETE", "/coupon/coupons/" + id, true);
    xhttp.send();
}

function loadShops() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var result = JSON.parse(this.response);
            var shopSelect = document.getElementById("stores-select");
            for (var i = 0; i < result.length; i++) {
                var option = document.createElement("option");
                option.value = result[i].shopName + '/' + result[i].id;
                option.text = result[i].shopName;
                shopSelect.add(option);
            }
        }
    };
    xhttp.open("GET", "/coupon/coupons/shops", true);
    xhttp.send();
}


function createCoupon(productName, oldPrice, newPrice, shopName, shopId) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            loadCoupons();
        }
    };

    xhttp.open("POST", "/coupon/coupons", true);
    xhttp.setRequestHeader("Content-Type", "application/json");

    var json = JSON.stringify({
        productName: productName,
        originalPrice: oldPrice,
        discountedPrice: newPrice,
        shop: {
            shopName: shopName,
            id: shopId
        }
    });
    console.log(json);

    // Saljemo objekat sa parametrima pretvoren u JSON
    xhttp.send(json);
}

window.onload = function () {
    loadCoupons();
    loadShops();
};

// Zakaci event listener na klik dugmeta
// document.getElementById("load-users-btn").addEventListener("click", loadUsers);


/*
Ovde ce se nalaziti logika koja se pozvati prilikom submit-a forme.
 */
function processForm(e) {
    if (e.preventDefault) e.preventDefault();
    var formData = new FormData(e.target);

    var productName = formData.get("productName");
    var oldPrice = formData.get("oldPrice");
    var newPrice = formData.get("newPrice");
    var shop = formData.get("stores");
    createCoupon(productName, oldPrice, newPrice, shop.split('/')[0], shop.split('/')[1]);

    // Obavezno vratiti false da bi se pregazilo default-no ponasanje prilikom submit-a.
    return false;
}

var form = document.getElementById('add-coupon-form');
if (form.attachEvent) {
    // IE support
    form.attachEvent("submit", processForm);
} else {
    form.addEventListener("submit", processForm);
}

// Dohvatanje query parametara iz url-a
// var urlParams = new URLSearchParams(window.location.search);
// var myParam = urlParams.get('myParam');