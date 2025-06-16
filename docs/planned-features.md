# 🛠️ Planned Features for DiscordLogger

This page outlines upcoming features and improvements planned for future versions of **DiscordLogger**. These updates are designed to enhance flexibility, usability, and the overall user experience for both server owners and players.

---

## 🚀 Feature Roadmap

| Feature                            | Status      | Target Version | Description |
|------------------------------------|-------------|----------------|-------------|
| Discord Embed support              | 🧪 In development | v1.1.0        | Sends logs as styled Discord embeds instead of plain text. |
| Per-event customization            | 🔜 Planned   | v1.1.0+        | Allow different formatting and toggles for each event. |
| Rich error reporting               | ✅ Partial   | v1.0.4         | Logs plugin-related issues to console and webhook (if safe). |
| Webhook test command               | 🔜 Planned   | v1.1.0         | `/discordlogger test` command to verify webhook setup. |
| Customizable embed templates       | 🔜 Planned   | v1.1.0+        | Users can modify embed titles, descriptions, colors, etc. |
| Multi-webhook support              | ❓ Under consideration | TBD   | Send different logs to different webhooks. |
| Reload config in-game              | 🔜 Planned   | v1.1.0         | `/discordlogger reload` to apply changes without restarting. |
| Server start/stop notifications    | 🔜 Planned   | v1.2.0         | Log when the server boots or shuts down. |
| Player advancement logging         | ✅ Partial   | v1.0.4         | May be expanded in future updates. |
| Config categories & documentation  | ✅ Completed | v1.0.4         | Config is now organized into sections with toggle options. |

---

## ✍️ Future Logging Options

The following logging types are planned for inclusion, with toggle support in `config.yml`.

| Event Type            | Description                                      |
|-----------------------|--------------------------------------------------|
| 🧍 Player Join/Quit    | Already supported in v1.0.4                      |
| 💬 Player Chat         | Logs chat messages from all players              |
| 📜 Server Commands     | Logs commands run via console or players         |
| 🔨 Block Break         | Logs when a player breaks a block                |
| 🧱 Block Place         | Logs when a player places a block                |
| 💀 Player Death        | Logs detailed death messages                     |
| 📈 Player Advancement  | Logs achievements or advancement unlocks         |
| 🚪 Server Start/Stop   | Logs server lifecycle events                     |
| ❗ Plugin Errors       | Logs recoverable plugin exceptions (if any)      |

All of these will be documented individually in [`logging-options.md`](logging-options.md).

---

## 💡 Possible Features (Community Feedback Welcome)

We are also considering the following, but would love your input:

- 🌐 Web-based dashboard for monitoring logs
- 🧩 Integration with other plugins (LuckPerms, EssentialsX, etc.)
- 🔍 Filter system to ignore certain players or actions
- ⏲️ Throttling to prevent spam during large events (e.g., TNT chain reactions)
- 👥 Nickname/tag customization per player in webhook

If you have ideas, feel free to [open an issue](https://github.com/LVCHLANN/DiscordLogger/issues/new/choose) or contribute!

---

## ⚙️ Planned `config.yml` Enhancements

The new `config.yml` will offer more clarity and structure. Here's a preview of what the embed settings might look like:

```yaml
use-embeds: true

embed-settings:
  use-timestamps: true
  default-color: "#00afff"
  show-server-name: true

events:
  player-chat:
    enabled: true
    embed:
      title: "💬 Chat"
      description: "**%player%** said: `%message%`"
      color: "#ffaa00"
```

---

## 🧠 Developer Features

For plugin developers and advanced users, future features may include:

- 📦 API endpoints or public interfaces for extending the logger
- 🧪 Unit tests for webhook handling and configuration parsing
- 🚫 Graceful fallback when webhook is invalid or rate-limited
- 📝 Verbose debug mode to help with plugin conflicts

---

## 💬 Contribute!

Want to help shape these features?

- 🧠 Suggest a new idea in [Discussions](https://github.com/LVCHLANN/DiscordLogger/discussions)
- 🛠️ Submit a PR or fork the repo
- 🐛 Report bugs early so we can fix them before major changes

---

<center><sub>We’re building this with the community. Your feedback shapes the future.</sub></center>
