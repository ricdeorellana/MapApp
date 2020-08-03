let map; 


			
			

function initMap(){

	map = new google.maps.Map(document.getElementById('map'), {
		center: coords,
		zoom: 5, 
	});
	let marker = new google.maps.Marker({
		position: coords,
		map: map,
		
	
		animation: google.maps.Animation.BOUNCE, 
	})
	
	
	
	let contentString = '<h2>' + city + ', ' + state + '</h2>';

	
	let infowindow = new google.maps.InfoWindow({ 
	content: contentString, 
	});
	
	google.maps.event.addListener(marker, 'click', function () {
	infowindow.open(map, marker);
});
}