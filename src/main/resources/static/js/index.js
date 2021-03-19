$(document).ready(function() {
    // let mapboxgl = require('mapbox-gl/dist/mapbox-gl.js');
    const mobileNav = document.getElementById("mobile-menu");
    const mobileBtn = document.getElementById("mobile-btn");
    const icon = document.querySelector("#user-menu");
    const menu = document.querySelector("#profile-menu");
    if(document.getElementById("map") !== null) {
        const key = document.getElementById("api").value;
        mapboxgl.accessToken = key
        let map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11'
        });

        const geocode = (input) => {
            fetch(`https://api.mapbox.com/geocoding/v5/mapbox.places/${input}.json?&access_token=${key}`)
                .then(res => res.json())
                .then(data => {
                    try {
                        map.flyTo({center: data.features[0].center})
                    } catch (e) {
                        console.log(e);
                    }
                });
        }
        $(document).on("click", "#location", function() {
            geocode(this.innerText)
        })
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
})

function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}