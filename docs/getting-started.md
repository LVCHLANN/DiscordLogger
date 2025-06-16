# 🚀 Getting Started with DiscordLogger

Welcome to **DiscordLogger**! This guide will walk you through installing and configuring the plugin on your Minecraft server.

---

## 🧱 Requirements

- A Minecraft server (e.g. Paper, Spigot, Purpur)
- Java 17 or newer
- A valid Discord Webhook URL

---

## 📦 Installation

1. Download the latest `.jar` from the [Releases](https://github.com/LVCHLANN/DiscordLogger/releases) page.
2. Drop it into your server’s `plugins/` folder.
3. Restart the server to generate the default configuration.

---

## ⚙️ Initial Configuration

After first launch, a config file will be created here:

`/plugins/DiscordLogger/config.yml`

You should then locate this line, and set your [Webhook URL](https://support.discord.com/hc/en-us/articles/228383668-Intro-to-Webhooks):

```yaml
webhook-url: "https://discord.com/api/webhooks/your-webhook-id"
```

You can toggle logging for different events using `true` or `false`.

📝 For a full breakdown of every setting, see the [Configuration Guide](configuration.md).

---

## 🧪 Testing the Plugin

Once installed and configured:

1. Join or leave the server as a player.
2. You should see a log message sent to the Discord channel via the webhook.

If nothing appears:
- Double-check the webhook URL.
- Check console logs for any startup errors.
- Make sure the plugin is loaded (`/plugins` should list `DiscordLogger` in green).

For help resolving issues, visit the [Troubleshooting Guide](troubleshooting.md).

---

## 📚 Next Steps

Here are some helpful pages to explore next:

- 🔧 [Configuration Guide](configuration.md)
- 📋 [Logging Options](logging-options.md)
- 🎨 [Webhook Formatting](webhook-formatting.md)
- 🛠️ [Planned Features](planned-features.md)
- ❓ [FAQ](faq.md)

---

## 🧠 Tip

Keep your Discord webhook **secret**. Do not share it publicly or commit it to version control.

---

<center><sub>Thanks for using DiscordLogger!</sub></center>
