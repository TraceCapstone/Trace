$(document).ready(function() {
    // let mapboxgl = require('mapbox-gl/dist/mapbox-gl.js');
    const mobileNav = document.getElementById("mobile-menu");
    const mobileBtn = document.getElementById("mobile-btn");
    const icon = document.querySelector("#user-menu");
    const menu = document.querySelector("#profile-menu");
    const deletePopup = $("#delete-popup");
    const tabContent = $(".tab-item");
    const tabs = $(".tab");
    if(document.getElementById("map") !== null) {
        const key = document.getElementById("api").value;
        mapboxgl.accessToken = key
        let map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11',
            zoom: 4,
            center: [-97.922, 39.381]
        });

        const geocode = (input) => {
            fetch(`https://api.mapbox.com/geocoding/v5/mapbox.places/${input}.json?&access_token=${key}`)
                .then(res => res.json())
                .then(data => {
                    try {
                        map.flyTo({center: data.features[0].center, essential: true, zoom: 15})
                    } catch (e) {
                        console.log(e);
                    }
                })
        }
        $(document).on("click", "#location", function() {
            geocode(this.innerText)
        })
    }

function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}


    if(mobileBtn !== null) {
        mobileBtn.addEventListener('click', function() {
            if (mobileNav.classList.contains('max-h-0')) {
                mobileNav.classList.add('max-h-96')
                mobileNav.classList.remove('max-h-0')
            }
            else {
                mobileNav.classList.add('max-h-0')
                mobileNav.classList.remove('max-h-96')
            }
        })
    }

    if (icon !== null) {
        icon.addEventListener('click', function(){
            if (menu.classList.contains("scale3d")) menu.classList.remove("scale3d")
            else menu.classList.add("scale3d")
        })
    }

    if($("#delete") !== null) {
        $(document).on("click", "#delete", function() {
            deletePopup.toggleClass('invisible')
        })
    }

    function showTabContent() {
        console.log("click");
        for (const content of tabContent) {
            $(content).addClass("hidden");
        }
        for (const content of tabContent) {
            if ($(content).hasClass(($(this).attr('id')))) {
                $(content).removeClass("hidden");
            }
        }
    }

    if(tabs !== null)
        tabs.on("click", showTabContent)
})
