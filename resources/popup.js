const postToSubframe = s => document.getElementById('subframe').contentWindow.postMessage(s, '*');

const injectContentScript = () => (
    chrome.tabs.query({
        active: true,
        lastFocusedWindow: true
    }, function(tabs) {
        if (tabs.length) {

            chrome.runtime.onMessage.addListener(postToSubframe);

            tabId = tabs[0].id;
            chrome.scripting.executeScript({
                target: {tabId: tabs[0].id},
                files: ['main.js', 'whamtet.chrome-extension.notify-text.js']
            });
        }
    })
);

addEventListener('message', injectContentScript);
