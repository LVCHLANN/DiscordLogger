# 🔌 DiscordLogger

![Version](https://img.shields.io/badge/version-v1.0.4-blue)  
![Minecraft](https://img.shields.io/badge/minecraft-1.21.5-green)  
![License](https://img.shields.io/github/license/LVCHLANN/DiscordLogger)  
![Status](https://img.shields.io/badge/status-active-brightgreen)  
![Contributions Welcome](https://img.shields.io/badge/contributions-welcome-yellow)  
![Java](https://img.shields.io/badge/built_with-Java_17-red)  
![Build System](https://img.shields.io/badge/build-Maven-blueviolet)

---

## 📖 Overview

**DiscordLogger** is a lightweight and flexible Minecraft server plugin designed to send server events directly to a Discord channel using webhooks. It empowers server administrators, moderators, and communities to monitor server activity in real-time without needing to be in-game or checking server logs manually.

Whether you're running a small private server or a large multiplayer network, DiscordLogger provides clear, concise, and structured logging output to Discord that enhances oversight, improves moderation response times, and increases player accountability.

Key benefits include:

- 🔍 Real-time event tracking via Discord
- 💬 Easy-to-read message formatting
- ⚙️ Fully configurable to match your needs
- 🚀 Extremely lightweight and fast
- 🧱 Works with any Bukkit-based Minecraft server (e.g., Paper, Spigot)

---

## ⚙️ Features in v1.0.4

The current version (v1.0.4) supports **basic logging via plain-text messages** using a simple configuration-based system.

### ✅ Available Event Logging Options

- 👋 **Player Join** – Log player join messages to Discord
- 🚪 **Player Quit** – Log when a player leaves the server
- 💬 **Player Chat** – Log chat messages (toggleable)
- ☠️ **Player Death** – Log death messages with cause
- 🧱 **Block Break** – Log block breaking (optional)
- 🪓 **Block Place** – Log block placing (optional)
- 📝 **Server Commands** – Log commands issued by the server console

### 🔐 Configurable Options

You can toggle each logging feature from the config file. The plugin also supports specifying a server name prefix for better identification across multiple servers.

---

## 🔧 Installation Guide

Setting up DiscordLogger takes only a few minutes!

### 📥 Step 1: Download

Download the latest `.jar` file from the [Releases](https://github.com/LVCHLANN/DiscordLogger/releases) page.

### 📁 Step 2: Install

Place the downloaded `.jar` file into your Minecraft server's `plugins/` directory.

### 🚀 Step 3: Start the Server

Launch or restart your server to allow the plugin to generate its config files.

### 🛠 Step 4: Configure

Open `plugins/DiscordLogger/config.yml` and paste in your Discord webhook URL. You can also toggle the events you want to log.

---

## 🧪 Example Output

Here’s what log messages look like in Discord with the default plain text format:

```
[Survival] Player Join: Lachlan  
[Survival] Player Chat: Lachlan: Let's explore!  
[Survival] Player Death: Lachlan tried to swim in lava
```

These messages appear instantly in the specified Discord channel, providing a live feed of key actions.

---

## 📚 Documentation

We provide detailed, easy-to-follow documentation for all aspects of the plugin.

- [🧾 Logging Options Explained](docs/logging-options.md)  
  Describes each logging toggle and when to use it

- [🛠 Configuration Reference](docs/configuration.md)  
  Explains every setting in `config.yml`

- [💡 Use Cases & Best Practices](docs/use-cases.md)  
  Learn how to use DiscordLogger effectively in various server scenarios

- [📥 Webhook Setup Guide](docs/webhook-setup.md)  
  Step-by-step instructions for creating and using Discord webhooks

- [🪲 Bug/Feature Issue Template](.github/ISSUE_TEMPLATE/bug_or_feature.yml)

- [🤝 Contributing Guidelines](.github/CONTRIBUTING.md)

---

## 🧪 Planned Features (Future Versions)

DiscordLogger will be greatly expanded in future releases. Here's what's on the roadmap:

### 🧾 Additional Logging Types

- 📢 Broadcast messages
- 🔧 Plugin enable/disable
- 🔀 Player teleportation
- 🧍 Entity deaths
- 🎖️ Player advancements
- 🎯 Player PvP and PvE hits
- 📥 Inventory interactions
- 💼 Staff activity (e.g. use of moderation tools)
- ⛏️ Block interactions (ignite, piston, explode)

### 🎨 Visual Enhancements

- 📦 Rich Discord **Embed** formatting  
- 🎨 Color-coded events by type  
- 💡 Toggleable Markdown in descriptions  
- 🎭 Emotes and icons for better readability

### 🧰 Technical Improvements

- 🔁 Hot config reloading  
- 🛑 Better error catching/logging  
- 🧩 Modular config with categorized toggles  
- 🧠 Contextual error messages for config issues  
- 📊 Performance impact metrics  
- 🔍 Keyword detection & alerts in player messages

---

## 🛠 Building From Source

If you wish to contribute or compile DiscordLogger yourself, here’s how:

### Requirements

- Java 17
- Maven 3.x

### Build Instructions

```bash
git clone https://github.com/LVCHLANN/DiscordLogger.git  
cd DiscordLogger  
mvn clean package
```

The final JAR file will be located in the `target/` folder.

---

## 🤝 Contributing

We welcome all kinds of contributions!

- 🐛 Found a bug? Open an issue or submit a fix.
- ✨ Have a feature in mind? Create a suggestion.
- 🧹 Want to help with formatting, localization, or docs? PRs welcome!

Before contributing, please read our [CONTRIBUTING.md](.github/CONTRIBUTING.md).

---

## 🐞 Reporting Issues

For bugs or feature requests, please use our [Bug/Feature Template](.github/ISSUE_TEMPLATE/bug_or_feature.yml).  
Be as detailed as possible to help us reproduce and resolve problems quickly.

---

## 📘 License

This project is licensed under the [MIT License](LICENSE).  
You are free to modify, share, and use the plugin even commercially — just leave attribution intact.

---

## 📣 Stay Connected

If you're interested in updates, future features, or discussions, follow the project on GitHub or star the repository to support development ❤️

---

<p align="center">
  Made with ❤️ by <a href="https://github.com/LVCHLANN">LVCHLANN (with additional programming done by ChatGPT)</a>
</p>
