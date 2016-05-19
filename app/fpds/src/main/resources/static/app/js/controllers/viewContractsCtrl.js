module.exports = function($scope,$http,$location,$routeParams,$anchorScroll,SearchFactory){
	$scope.contractId = $routeParams.contractId;
	$scope.expanded = false;
	$scope.displays = false;
	$scope.loading = 0;

	var noOfContracts;
	$scope.flag = [];
	for(var i=0; i < noOfContracts ;i++){
		$scope.flag[i] = false;
	}

	$scope.company = function(value){
		console.log(value);
		return SearchFactory.getAutocomplete(value).then(function(res){
			return res.data.map(function(item){
				return item;
			});
		});
	};

	$scope.onSelect = function ($item, $model, $label) {                
	    $scope.choosen = $item.field_value;    
	    $scope.search();
	  };

	$scope.search = function(){
		var earl = '/search/' + $scope.query;
    	$location.path(earl);
	};

	$scope.expand = function(){
		$scope.expanded = !$scope.expanded;
		$scope.displays = !$scope.displays;
		if($scope.displays === true){
			for(var i=0; i < noOfContracts; i++){
				$scope.flag[i] = true;
			}	
		}
		else{
			for(var x=0; x < noOfContracts; x++){
				$scope.flag[x] = false;
			}
		}
		
	};

	$scope.tranExpand = function(val){
		console.log(val);
		$scope.flag[val] = !$scope.flag[val];
	};

	$scope.back = function(){
		var rl = '/search/' + $scope.vendor;
		$location.path(rl);
	};
	$scope.gototop = function(){
		console.log("Entered");
		$location.hash('top1');
		$anchorScroll();
	};
	$scope.totalContractValue = 0;
	SearchFactory.getViewContracts($scope.contractId)
	.success(function(data){
		$scope.contracts = data;
		$scope.vendor = data[0].vendorname;
		console.log("Here is " + $scope.contracts.length);
		for(var i=0; i < $scope.contracts.length; i++){
			$scope.totalContractValue = $scope.totalContractValue + $scope.contracts[i].dollarsobligated;
			//console.log($scope.totalContractValue);
		}
		noOfContracts = $scope.contracts.length;
		$scope.loading = 1;
	})
	.error(function(header,config,status,data){
		console.log(status);
	});

	

};