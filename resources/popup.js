let tabId;

chrome.tabs.query({
    active: true,
    lastFocusedWindow: true
}, function(tabs) {
    if (tabs.length) {
        tabId = tabs[0].id;
        chrome.scripting.executeScript({
            target: {tabId},
            files: ['main.js', 'whamtet.chrome-extension.listen-for-events.js']
        });
    }
});

addEventListener('message', e => {
    const uuid = e.data;
    chrome.tabs.sendMessage(tabId, uuid);
});
