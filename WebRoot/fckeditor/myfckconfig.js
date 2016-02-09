FCKConfig.FontNames		= '宋体;黑体;楷体_GB2312;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana' ;
FCKConfig.FontSizes		= 'smaller/较小;larger/较大;xx-small/极细字;x-small/细字;small/小字体;medium/中字体;large/大字体;x-large/加大字;xx-large/特大字' ;

FCKConfig.AutoDetectLanguage = false ;
FCKConfig.DefaultLanguage = "zh-cn" ;
FCKConfig.StartupFocus = false ;

FCKConfig.LinkUpload = true ;
FCKConfig.LinkBrowser = true ;

FCKConfig.ImageBrowser = true ;

FCKConfig.SkinPath = FCKConfig.BasePath + 'skins/office2003/' ;

FCKConfig.Plugins.Add('simplecommands');
//FCKConfig.Plugins.Add('attachment','zh-cn');

FCKConfig.ToolbarSets["site"] = [
	['Source','Preview','-','Save','Paste','PasteText','PasteWord','-'],
	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	['Bold','Italic','Underline','StrikeThrough'],
	['OrderedList','UnorderedList'],
	'/',
	['StyleSimple','FontFormatSimple','FontNameSimple','FontSizeSimple'],
	['TextColor','BGColor'],
	['Outdent','Indent','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
	['Link','Unlink','Smiley','Image','Flash','SpecialChar','PageBreak','FitWindow']
];
FCKConfig.ToolbarSets["myBasic"] = [
	['Bold','Italic','-','OrderedList','UnorderedList','-','Link','Unlink','-','Smiley']
] ;