module.exports = function($scope,$http,$location,$routeParams,SearchFactory){
	$scope.idvnumber = $routeParams.idvId;

	$scope.vendor = "OCTO CONSULTING GROUP, INC.";
	$scope.expanded = [];
	$scope.flag = [];

	$scope.company = function(value){
		console.log(value);
		return SearchFactory.getVendor(value).then(function(res){
			return res.data.map(function(item){
				return item;
			});
		});
	};

	$scope.search = function(){
		var earl = '/search/' + $scope.query;
    	$location.path(earl);
	};

	SearchFactory.getViewIdv($scope.idvnumber)
	.success(function(data){
		console.log(data);
		//$scope.contractDetails = data;
	})
	.error(function(header,status,config,data){
		console.log(status);
	});

	$scope.contractDetails = [{piid : "0002", contracts : [{vendorname : "BATTELLE MEMORIAL INSTITUTE",currentcompletiondate : "2014-06-30",dollarsobligated:-310639.57,effectivedate:"2015-09-30",modnumber:5,reasonformodification:"SUPPLEMENTAL AGREEMENT FOR WORK WITHIN SCOPE"},
														   {vendorname : "BATTELLE MEMORIAL INSTITUTE",currentcompletiondate : "2014-06-30",dollarsobligated:-310639.57,effectivedate:"2015-09-30",modnumber:5,reasonformodification:"SUPPLEMENTAL AGREEMENT FOR WORK WITHIN SCOPE"}]},
														   {piid : "0003", contracts : [{vendorname : "BATTELLE MEMORIAL INSTITUTE",currentcompletiondate : "2014-06-30",dollarsobligated:-310639.57,effectivedate:"2015-09-30",modnumber:5,reasonformodification:"SUPPLEMENTAL AGREEMENT FOR WORK WITHIN SCOPE"},
														   {vendorname : "BATTELLE MEMORIAL INSTITUTE",currentcompletiondate : "2014-06-30",dollarsobligated:-310639.57,effectivedate:"2015-09-30",modnumber:5,reasonformodification:"SUPPLEMENTAL AGREEMENT FOR WORK WITHIN SCOPE"},{vendorname : "BATTELLE MEMORIAL INSTITUTE",currentcompletiondate : "2014-06-30",dollarsobligated:-310639.57,effectivedate:"2015-09-30",modnumber:5,reasonformodification:"SUPPLEMENTAL AGREEMENT FOR WORK WITHIN SCOPE"}]}];

	var mainButtons = $scope.contractDetails.length;
	console.log("Main " +mainButtons);
	var innerMax;

	for(var i = 0 ; i< mainButtons; i++){
		$scope.expanded[i] = false;
	}
	console.log(innerMax);

	for(var x = 0; x < $scope.contractDetails.length ; x++){
		$scope.flag[x] = [];
		for(var y = 0; y < $scope.contractDetails[x].contracts.length; y++){
			$scope.flag[x][y] = false;
		}
	}

	$scope.expand = function(val){
		$scope.expanded[val] = !$scope.expanded[val];
		for(var i = 0; i< $scope.contractDetails[val].contracts.length; i++){
			if($scope.expanded[val] === true){
				$scope.flag[val][i] = true;
			}
			else{
				$scope.flag[val][i] = false;
			}
		}
	};

	$scope.tranExpand = function(parent,child){
		console.log("Parent " + parent +"Child " + child);
		$scope.flag[parent][child] = !$scope.flag[parent][child];
	};
};