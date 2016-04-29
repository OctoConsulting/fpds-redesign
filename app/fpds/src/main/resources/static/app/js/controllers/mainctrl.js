module.exports = function($scope,$http,$location,SearchFactory) {
  console.log("required!!");
    
  SearchFactory.getItems();

  var records;
  
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

  $scope.period = 'Past Year'; 
  $scope.denom = '';

  SearchFactory.getContract('Past Year')
    .success(function(result){
      $scope.noOfContracts = result.total_contracts;
      $scope.totalContractsValue = convertToMill(result.total_award_value);
    })
    .error(function(data,status,header,config){
      console.log(data);
    });

  SearchFactory.getPrime('Past Year')
    .success(function(result){
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
                    { name: "Contract Value" , width: '17.7%', field: "contract_value" , cellFilter : 'currency',cellClass: 'grid-align'}],
        enableGridMenu: true,
        enableSelectAll: true,
        exporterCsvFilename: 'PrimeAwards.csv',
        exporterPdfDefaultStyle: {fontSize: 9},
        exporterPdfTableStyle: {margin: [30, 30, 30, 30]},
        exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'blue'},
        exporterPdfHeader: { text: "Top Prime Contracts", style: 'headerStyle' },
        exporterPdfFooter: function ( currentPage, pageCount ) {
          return { text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle' };
        },
        exporterPdfCustomFormatter: function ( docDefinition ) {
          docDefinition.styles.headerStyle = { fontSize: 22, bold: true };
          docDefinition.styles.footerStyle = { fontSize: 10, bold: true };
          return docDefinition;
        },
        exporterPdfOrientation: 'portrait',
        exporterPdfPageSize: 'LETTER',
        exporterPdfMaxGridWidth: 500,
        exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
        onRegisterApi: function(gridApi){
            $scope.gridApi = gridApi;
        }
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
      records = result.length;
      $scope.primeAwards = result;
    })
    .error(function(data,status,header,config){
      console.log(status);
    });
  };

  $scope.onSelect = function ($item, $model, $label) {                
    $scope.choosen = $item.field_value;
  };

  $scope.search = function(){
    console.log("Search");
    console.log($scope.choosen);
    //console.log("Cho " + $scope.cho);
    var earl = '/search/' + $scope.query;
    $location.path(earl);
  };
  
  convertToMill = function(val){
    //val = val/1000000;
    //val = Math.round(val*100)/100;
    if(val === 0){
      $scope.denom = "";
      return val;
    }
    else{
      console.log("Value " + val);
      var temp = Math.floor(Math.log10(Math.round(val))) + 1;
      console.log(temp);
      if(temp > 6 && temp <= 9){
        val = val/1000000;
        val = Math.round(val*100)/100;
        $scope.denom = 'Millions';
        return val;
      }
      else if(temp > 9){
        val = val/1000000000;
        val = Math.round(val*100)/100;
        $scope.denom = 'Billions';
        return val;
      }
      else{
        $scope.denom = "";
        return val;  
      }
    }        
  };
};