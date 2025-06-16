# DiscordLogger

A Minecraft plugin that logs key server events and sends them to a Discord webhook — perfect for server owners, moderators, or admins who want to monitor activity in real time, even when they're not online.

---

## ✅ Current Features (v1.0.4)

- 🔗 Webhook integration with custom server name support
- 📥 Simple, readable text messages for Discord
- 🎮 Player-based logging:
  - Player join
  - Player quit
  - Player chat
  - Player death
- ⛏️ Block event logging:
  - Block break
  - Block place
- 🛠️ Server command logging:
  - Commands run from console

---

## 🔧 Configuration

Modify `config.yml` to toggle each event individually and set your webhook URL:

```yaml
server-name: "MyServer"
webhook-url: "https://discord.com/api/webhooks/your-webhook-id"

player-events:
  log-player-join: true
  log-player-quit: true
  log-player-chat: true
  log-player-death: true

block-and-world-events:
  log-block-break: false
  log-block-place: false

server-admin-events:
  log-server-command: false
```

For the full configuration file layout, see [`config.yml`](./src/main/resources/config.yml).

---

## 📚 Documentation

Learn more about each event category and when/why to enable them:

- [🧍 Player Events](docs/player-events.md)
- [⛏️ Block & World Events](docs/block-events.md)
- [🐮 Entity Events](docs/entity-events.md)
- [🛠️ Server Admin Events](docs/server-admin-events.md)
- [🎒 Inventory Events](docs/inventory-events.md)
- [🧪 Custom Events](docs/custom-events.md)

---

## 🚀 Planned Features

Coming in future versions:

- 📦 Discord embeds for rich formatting
- 🧹 Cleaner formatting per event type
- 📄 Additional logging:
  - Player commands, interactions, advancement, item pickup/drop
  - Entity spawn, damage, and death
  - World events (ignite, burn, chunk load/unload)
  - Inventory and GUI usage
- 🔍 Keyword/command alerts
- 👥 Staff mode, IP logins, AFK tracking
- 🌐 Remote command logging and plugin state changes

---

## 📥 Installation

1. Download the latest release from the [Releases tab](https://github.com/LVCHLANN/DiscordLogger/releases)
2. Drop the `.jar` into your server’s `/plugins/` folder
3. Restart the server
4. Edit `config.yml` and reload the plugin

---

## 🤝 Contributing

Pull requests are welcome! Please:
- Match the code style used in the project
- Test changes thoroughly
- Document new features in the appropriate `docs/*.md` file

---

## 🪪 License

This plugin is licensed under the MIT License. See [LICENSE](./LICENSE) for more info.

---
