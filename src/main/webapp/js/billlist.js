var billObj;

//订单管理页面上点击删除按钮弹出删除框(billlist.jsp)
function deleteBill(obj){
	$.ajax({
		type:"GET",
		url:path+"/jsp/delbill.do",
		data:{id:obj.attr("billid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.delResult == "false"){//删除失败
				//alert("对不起，删除订单【"+obj.attr("billcc")+"】失败");
				changeDLGContent("对不起，删除订单【"+obj.attr("billcc")+"】失败");
			}else if(data.delResult == "notexist"){
				//alert("对不起，订单【"+obj.attr("billcc")+"】不存在");
				changeDLGContent("对不起，订单【"+obj.attr("billcc")+"】不存在");
			}
		},
		error:function(data){
			alert("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	$(".viewBill").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/bill.do?method=view&billid="+ obj.attr("billid");
	});
	
	$("tr:gt(0)").mouseenter(function(){
		$("#info").show();
		var id = $(this).attr("data-id");
		$.post(path+"/jsp/bView.do",{"id":id},function(data){
			$("#billCode").html(data.billCode);
			$("#productName").html(data.productName);
			if(data.isPayment=="1"){
				$("#isPayment").html("未付款");
			}else if(data.isPayment=="2"){
				$("#isPayment").html("已付款");
			}
			$("#productUnit").html(data.productUnit);
			$("#productCount").html(data.productCount);
			$("#totalPrice").html(data.totalPrice);
			$("#proName").html(data.proName);
		},"json");
	}).mouseleave(function(){
		$("#info").hide();
	});
	$("tr:gt(0)").mousemove(function(event){
		var x = event.clientX;
		var y = event.clientY;
		$("#info").css({"top":y+"px","left":x+"px"});
	});
	
	$(".modifyBill").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/modifyBill/"+ obj.attr("billid");
	});
	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteBill(billObj);
	});

	$(".deleteBill").on("click",function(){
		billObj = $(this);
		changeDLGContent("你确定要删除订单【"+billObj.attr("billcc")+"】吗？");
		openYesOrNoDLG();
	});
	
});