(function(){
    // let mapboxgl = require('mapbox-gl/dist/mapbox-gl.js');
    const local = document.getElementById("location");
    console.log(local);
    mapboxgl.accessToken = mapboxKey
    let map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11'
    });

    const geocode = (input) => {
        fetch(`https://api.mapbox.com/geocoding/v5/mapbox.places/${input}.json?&access_token=${mapboxKey}`)
            .then(res => res.json())
            .then(data => {
                try {
                    console.log(data);
                } catch {
                    console.log("error");
                }
            });
        if (local !== null) {
            local.addEventListener('click', function() {
                console.log("test");
                geocode(this.innerText);
            })
        }
    }
})()

function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}