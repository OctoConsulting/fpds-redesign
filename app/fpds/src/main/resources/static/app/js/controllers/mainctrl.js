module.exports = function($scope,$http,SearchFactory) {
  console.log("required!!");
  
  
  SearchFactory.getItems();

  
  	$scope.cities = function(cityName){
    	console.log(cityName);
    	return SearchFactory.getVendor(cityName).then(function(res){
        //$scope.value = res.data;
        
        return res.data.map(function(item){
          
          //console.log(item.vendorname);
        	return item;
        }); 
      });  
    };	
};