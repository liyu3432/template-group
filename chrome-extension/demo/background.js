//content-script 动态注入案例
chrome.action.onClicked.addListener(async (tab) => {
	console.log(tab);
	if (tab.url.startsWith('https://www.baidu.com')) {
		console.log('this is baidu page');
		chrome.scripting.executeScript({
      target: {
				tabId: tab.id,
			},
      files: ['exec.js'],
			// func: function
			world: 'MAIN'
    },)
	} else {
		console.log('this is not baidu page');
	}
});
