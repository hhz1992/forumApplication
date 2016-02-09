/**
 * @param input 验证码输入框jquery对象。
 * @param url 验证码的url地址。
 * @param top 上下偏移量。
 */
CheckCode = function(input, url, top) {
	this.input = input;
	this.url = url || 'checkcode.jpg';
	this.top = top || 45;
	this.imgLayer = null;
	this.img = null;
	this.event = null;
	this.isShow = false;
	var o = this;
	var showImg = function() {
		if (o.imgLayer == null) {
			o.createHtml();
		}
		if (!o.isShow) {
			var d = new Date().getTime();
			o.img.attr('src', o.url + '?d=' + d);
			var offset = o.input.offset();
			o.imgLayer.show();
			o.imgLayer.css('top','200px');
			o.imgLayer.css('left', offset.left+20 + 'px');
			//o.imgLayer.css('top', offset.top - o.top + 'px');
			//o.imgLayer.css('left', offset.left + 'px');
			o.isShow = true;
		}
	};
	var hideImg = function() {
		if (o.isShow) {
			o.event = setTimeout(function() {
				o.imgLayer.hide();
				o.isShow = false;
			}, 200);
		}
	};
	this.input.bind('focus', showImg);
	this.input.bind('blur', hideImg);
};
CheckCode.prototype.createHtml = function() {
	this.imgLayer = $('<div/>');
	this.img = $('<img border="0" alt="验证码看不清楚?请点击刷新验证码" title="验证码看不清楚?请点击刷新验证码"/>');
	var o = this;
	this.img.bind('click', function() {
		o.input.focus();
		if (o.event) {
			clearTimeout(o.event);
		}
		this.src = o.url + '?d=' + new Date().getTime();
	});
	this.img.appendTo(this.imgLayer);
	this.imgLayer.appendTo(document.body);
	this.imgLayer.addClass('j-chkcode');
};