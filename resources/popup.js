chrome.tabs.query({
    active: true,
    lastFocusedWindow: true
}, function(tabs) {
    chrome.scripting.executeScript({
        target: {tabId: tabs[0].id},
        files: ['main.js', 'whamtet.chrome-extension.print.js']
    });
});
