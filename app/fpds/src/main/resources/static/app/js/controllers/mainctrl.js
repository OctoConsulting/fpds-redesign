module.exports = function($scope,$http,limitToFilter,SearchFactory) {
  console.log("required!!");
  
  $scope.value = [];
  SearchFactory.getItems();

  SearchFactory.getSearch()
  	.success(function(data){
  		$scope.vendorData = data;
  	})
  	.error(function(data,status,headers,config){
  		//handle error
  	});

  	$scope.cities = function(cityName){
    	console.log(cityName);
    	SearchFactory.getVendor(cityName).then(function(res){
        $scope.value = res.data;
        
        return res.data.map(function(item){
          
          console.log(item.vendorname);
        	return item;
        }); 
      });  
    };	
};