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

  $scope.period = 'Year'; 
  SearchFactory.getContract('Year')
    .success(function(result){
      $scope.noOfContracts = result.total_contracts;
      $scope.totalContractsValue = convertToMill(result.total_award_value);
    })
    .error(function(data,status,header,config){
      console.log(data);
    });

  
  $scope.filter= function(value){
    console.log(value);
    $scope.period = value;
    SearchFactory.getContract(value)
      .success(function(totalAwards){
        $scope.noOfContracts = totalAwards.total_contracts;
        $scope.totalContractsValue = convertToMill(totalAwards.total_award_value);
      })
      .error(function(data,status,header,config){
        console.log(data);
      });
  };	

  convertToMill = function(val){
    val = val/1000000;
    val = Math.round(val*100)/100;
    return val;
  };
};