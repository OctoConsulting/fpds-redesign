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

  SearchFactory.getPrime('Year')
    .success(function(result){
      console.log(result);
      $scope.primeAwards = result;
    })
    .error(function(data,status,header,config){
      console.log(status);
    });

    $scope.gridOptions = {
        data: 'primeAwards',
        enablePinning: true,
        columnDefs: [{ name : "Agency" , width: '30%', field: "agency"},
                    { name: "Company Name" , width: '30%', field: "company" },
                    { name: "Task Order" , width: '20%', field: "task_order"},
                    { name: "Contract Value" , width: '20%', field: "contract_value" , cellFilter : 'currency'}]
    };

    
  
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

    SearchFactory.getPrime(value)
    .success(function(result){
      console.log(result.length);
      if (typeof result === "undefined"){
        console.log("Entered");
      }
      $scope.primeAwards = result;
    })
    .error(function(data,status,header,config){
      console.log(status);
    });
  };	

  convertToMill = function(val){
    val = val/1000000;
    val = Math.round(val*100)/100;
    return val;
  };
};