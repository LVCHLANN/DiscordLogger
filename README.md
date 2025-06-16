# 📘 DiscordLogger

**DiscordLogger** is a lightweight Minecraft plugin that logs player activity and server events to a Discord channel using a webhook.

Currently in early development (**v1.0.4**), DiscordLogger offers basic plain-text logging for select events — but aims to become a full-featured logging and moderation tool for Minecraft server owners.

---

## ✅ Current Features (v1.0.4)

✔️ Logs the following events to Discord using **plain text** messages:

- Player Join
- Player Quit
- Player Chat
- Player Death
- Block Break
- Block Place
- Server Commands

✔️ Configurable via a simple `config.yml`  
✔️ Uses a Discord webhook for message delivery  
✔️ Lightweight and easy to install  
✔️ Live configuration reloading with `/discordlogger reload`

---

## ⚙️ Configuration

After running the plugin once, a `config.yml` file will be generated inside the `plugins/DiscordLogger/` directory.

You can toggle which events get logged by editing the file:

```yaml
server-name: "MyServer"
webhook-url: "https://discord.com/api/webhooks/your-webhook-id"

log-player-join: true
log-player-quit: true
log-player-chat: true
log-server-command: false
log-block-break: false
log-block-place: false
log-player-death: false
```

Use `/discordlogger reload` to apply config changes without restarting the server.

---

## 📦 Installation

1. Download the latest release from the [Releases Page](https://github.com/LVCHLANN/DiscordLogger/releases).
2. Place the `.jar` file into your server's `plugins/` folder.
3. Restart the server.
4. Edit `plugins/DiscordLogger/config.yml` to match your desired logging setup.
5. Reload the config (optional):  
   ```bash
   /discordlogger reload
   ```

> 🔧 You must supply a valid [Discord Webhook URL](https://support.discord.com/hc/en-us/articles/228383668-Intro-to-Webhooks) in the config file.

---

## 📋 Example Log Message (v1.0.4)

```
[16-06-2025 14:30:12] Player Join: [Survival] Steve
[16-06-2025 14:32:00] Player Chat: [Creative] Alex: Hello!
[16-06-2025 14:33:45] Block Break: [Survival] Steve broke STONE at X:123 Y:64 Z:-45
```

Messages are delivered in **plain text**, timestamped, and prefixed with the server name (if configured).

---

## 🚧 Planned Features

These features are not available in v1.0.4 but are actively being developed for future versions:

### 🔜 Event Logging Expansion
- Teleports, Kicks, Bans, Advancements
- Inventory actions (open, click, trades)
- Entity spawn, damage, and death
- Server lifecycle events (plugin enable/disable, world saves)
- AFK detection, staff mode toggles, suspicious commands

### 🎨 Improved Message Formatting
- Full **Discord Embed** support
- Color-coded categories and icons
- Contextual fields like world, coordinates, item names, etc.

### 🛠️ Config File Overhaul
- Category-based organization for event toggles
- Cleaner structure and optional advanced settings

### ⚠️ In-Discord Error Logging
- User-friendly formatting for plugin-level (not webhook) errors
- Excludes Discord webhook delivery failures

### 🔁 Multi-Webhook Support (Planned)
- Different event categories can be routed to different channels

---

## 🧪 Commands

| Command                    | Description                    | Permission       |
|----------------------------|--------------------------------|------------------|
| `/discordlogger reload`    | Reloads the config on the fly  | `discordlogger.reload` |

---

## 🤝 Contributing

Have an idea? Want to help code? Contributions are welcome!

See [CONTRIBUTING.md](CONTRIBUTING.md) (coming soon) for how to get started.

---

## 📄 License

Licensed under the [MIT License](LICENSE).

---

## 🔗 Useful Links

- [Releases](https://github.com/LVCHLANN/DiscordLogger/releases)
- [Issue Tracker](https://github.com/LVCHLANN/DiscordLogger/issues)
- Planned docs coming soon: `docs/` folder with detailed explanations of each logging category
