# 🔌 DiscordLogger

![Version](https://img.shields.io/badge/version-v1.0.4-blue)  
![Minecraft](https://img.shields.io/badge/minecraft-1.20.x-green)  
![License](https://img.shields.io/github/license/LVCHLANN/DiscordLogger)  
![Status](https://img.shields.io/badge/status-active-brightgreen)  
![Contributions Welcome](https://img.shields.io/badge/contributions-welcome-yellow)  
![Java](https://img.shields.io/badge/built_with-Java_17-red)

---

## 📖 Overview

**DiscordLogger** is a lightweight Minecraft server plugin designed to send server events and player activity logs directly to a Discord channel via webhook.

With minimal configuration, server admins can receive real-time notifications for key Minecraft events, enhancing server monitoring and moderation.

---

## ⚙️ Features (v1.0.4)

Currently, DiscordLogger supports **plain text logging** for a core set of Minecraft events, including:

- 👋 Player Join  
- 🚪 Player Quit  
- 💬 Player Chat  
- ☠️ Player Death  
- 🧱 Block Break  
- 🪓 Block Place  
- 📝 Server Command Execution  

Each event log can be individually toggled on or off in the configuration file, giving admins control over what information is sent to Discord.

> ⚠️ **Important:**  
> Advanced features such as **Discord embed support**, **rich formatting**, **extended event logging**, and **improved error handling** are planned for future releases.

---

## 🚀 Installation

Follow these steps to get started with DiscordLogger:

1. Download the latest release `DiscordLogger-v1.0.4.jar` from the [Releases](https://github.com/LVCHLANN/DiscordLogger/releases) page.  
2. Place the JAR file into your Minecraft server's `plugins` directory.  
3. Start or restart your Minecraft server to generate the default `config.yml` file.  
4. Open the `config.yml` and configure the plugin according to your preferences (see below).

---

## 🛠 Configuration

The configuration file `config.yml` is created upon the plugin's first run in the `plugins/DiscordLogger` folder.

### Example `config.yml` (v1.0.4):
```yaml
server-name: "MyMinecraftServer"  
webhook-url: "https://discord.com/api/webhooks/your-webhook-id"

log-player-join: true  
log-player-quit: true  
log-player-chat: true  
log-player-death: true  
log-block-break: false  
log-block-place: false  
log-server-command: false
```

### Configuration Options Explained:

- **server-name**: The name used to identify your server in Discord messages. This helps distinguish logs if you manage multiple servers.  
- **webhook-url**: The Discord webhook URL where the logs will be sent. Create this in your Discord server settings under Integrations > Webhooks.  
- **log-player-join**: Enable or disable logging of player join events.  
- **log-player-quit**: Enable or disable logging of player quit events.  
- **log-player-chat**: Enable or disable logging of chat messages sent by players.  
- **log-player-death**: Enable or disable logging of player deaths with details.  
- **log-block-break**: Enable or disable logging of block break events.  
- **log-block-place**: Enable or disable logging of block place events.  
- **log-server-command**: Enable or disable logging of commands executed on the server console.

---

## 📝 Usage

Once installed and configured:

- All enabled events will be sent as plain text messages to your configured Discord webhook.  
- Each log message includes the server name and event details, making it easy to track activity in your Minecraft server from Discord.  
- Example of a player join message in Discord:  
  `[MyMinecraftServer] Player Join: PlayerName has joined the game.`

---

## 🔮 Planned Features

The following features are planned for upcoming versions of DiscordLogger to make it more powerful and user-friendly:

- ✨ **Discord Embed Support**  
  Rich, visually appealing embed messages with colors, icons, and timestamps to improve readability and context.  

- 🔄 **Expanded Event Logging**  
  Covering additional events such as player commands, teleportation, bans, kicks, advancements, inventory interactions, and more.  

- 🛡️ **Advanced Error Handling**  
  Errors and warnings will be logged and sent via Discord with clear, actionable messages to help server admins diagnose problems quickly.  

- ⚙️ **Config File Categorization**  
  Grouping configuration options into categories such as player events, block events, server events, and custom events for easier management.  

- 📚 **Detailed Documentation**  
  Providing separate markdown files explaining each event type, use cases, and configuration tips for new and experienced users alike.

---

## 📚 Documentation

To help you understand and make the most of DiscordLogger, detailed documentation pages are included in the repository under the `/docs` folder. Each page covers specific event types and features in depth:

- [Player Events](docs/player-events.md) – Learn about the player-related events you can log and how to configure them.  
- [Block and World Events](docs/block-and-world-events.md) – Details on logging block changes and world events.  
- [Entity Events](docs/entity-events.md) – Explanation of entity-based events planned for future updates.  
- [Server Admin Events](docs/server-admin-events.md) – Information on server-side commands and administrative logs.  
- [Inventory Events](docs/inventory-events.md) – How inventory interactions will be logged.  
- [Custom Events](docs/custom-events.md) – Plans for customizable and keyword-based logging.  
- [Error Handling](docs/error-handling.md) – Guidance on error logging and troubleshooting.

---

## 🤝 Contributing

Your contributions make DiscordLogger better! Whether it's reporting bugs, suggesting new features, or submitting code improvements, you’re welcome to participate.

Please see [CONTRIBUTING.md](CONTRIBUTING.md) for detailed contribution guidelines, including code style, branch management, and how to submit pull requests.

---

## 🧑‍💻 Development & Build Information

- Developed using **Java 17** to ensure compatibility with modern Minecraft server versions.  
- The plugin is currently built using Maven.
- Source code is located in `/src/main/java`.  
- Contributions to introduce build automation are welcome.

---

## 📄 License

DiscordLogger is licensed under the permissive [MIT License](LICENSE), allowing free use, modification, and distribution.

---

## 🗨 Support & Contact

Need help or want to share feedback? Please open an issue on the GitHub repository:

[GitHub Issues](https://github.com/LVCHLANN/DiscordLogger/issues)

---

<p align="center">
Made with ❤️ by LVCHLANN  
</p>
