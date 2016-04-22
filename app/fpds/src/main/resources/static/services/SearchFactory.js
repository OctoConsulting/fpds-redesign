module.exports = function($http){

	var factory = {};

	factory.getItems = function(){
		$http.get("/total").success(function(data) {
    		console.log(data);
  		});
	};

	factory.getSearch = function(){
			return $http.get("http://localhost:8080/search?q=" + "octo");
		
	};

	factory.getVendor = function(vendorName){
		console.log("Vendor " + vendorName);
		return $http.get("/search?q=" + vendorName);
	};
	
	return factory;
};