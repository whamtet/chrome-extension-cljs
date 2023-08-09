chrome.action.onClicked.addListener((tab) => {
    chrome.scripting.executeScript({
        target: {tabId: tab.id},
        files: ['main.js', 'whamtet.chrome-extension.print.js']
    });
});
