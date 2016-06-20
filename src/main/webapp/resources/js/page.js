(function(){
	var
		//单步执行用到的
		i=0,
		length,
		responseArray;
	//一步执行
	$('.J_run').on('click', function(){
		var 
			length,
			newVal,
			control = $('.J_control').val(),
			denv = $('.J_denv').val();

		$.post('http://localhost:8080/calculate', {control:control,denv:denv}, function(data){
			$('.J_control').val("");
			$('.J_stack').val("");
			for(j = 0; j < data.list.length; j++){
				newVal = $('.J_list').val() + '\r\n' + data.list[j].list + '\r\n'+ data.list[j].rule;
				$('.J_list').val(newVal).addClass('in');
			}
			$('.J_run').val('END');
		})
	})
	//单步
	$('.J_submit').on('click', function(){
		var
			control = $('.J_control').val(),
			denv = $('.J_denv').val();
		if($('.J_submit').attr('hasSubmit') == 0){
			$.post('http://localhost:8080/calculate', {control:control,denv:denv}, function(data){
				console.log(data);
				responseArray = data.list;
				length = data.list.length;
				//第一步
				renderData();
				$('.J_submit').attr('hasSubmit','1').val('Next');
				return;
			})
		}
		renderData();
	})

	function renderData(){
		if(i < length){
			$('.J_control').removeClass('in');
			$('.J_stack').removeClass('in');
			$('.J_list').removeClass('in');
			if(responseArray[i].control){
				$('.J_control').val(responseArray[i].control).addClass('in');
			}
			if(responseArray[i].stack){
				$('.J_stack').val(responseArray[i].stack).addClass('in');
			}
			if(responseArray[i].list){
				newVal = $('.J_list').val() + '\r\n' + responseArray[i].list + responseArray[i].rule;
				$('.J_list').val(newVal).addClass('in');
			}
			i++;
		}else{
			$('.J_submit').val('END')
		}
	}
})()