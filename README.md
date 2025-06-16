# DiscordLogger

A Spigot/Paper plugin that sends Minecraft server logs to a Discord channel via webhooks.

---

## ⚙️ Features

- **Real-time Log Forwarding**: Automatically sends server logs to a specified Discord webhook.
- **Selective Logging**: Customize which log types are forwarded (e.g., chat messages, player actions).
- **Dynamic Configuration**: Use the `/discordlogger reload` command to apply changes without restarting the server.
- **Proxy Support**: Enhanced logging to indicate the origin of messages.
- **Improved Formatting**: Future updates will include clearer and more detailed log formatting.

---

## 🚀 Installation

1. Download the latest `.jar` file from the [Releases](https://github.com/LVCHLANN/DiscordLogger/releases) section.
2. Place the `.jar` file into your server's `plugins` directory.
3. Restart your server to generate the configuration files.
4. Edit `config.yml` to set your Discord webhook URL and adjust logging preferences.

---

## 🛠️ Configuration

The plugin's behavior is controlled via the `config.yml` file located in the `plugins/DiscordLogger` directory. Key settings include:

- `webhook_url`: Your Discord webhook URL.
- `log_chat`: Set to `true` to log chat messages.
- `log_player_actions`: Set to `true` to log player actions.

After making changes to the configuration, use the `/discordlogger reload` command to apply them without restarting the server.

---

## 📦 Planned Features

- **Enhanced Proxy Support**: Improve clarity on log origins.
- **Advanced Formatting Options**: Offer more detailed and customizable log formats.
- **Additional Logging Options**: Provide more granular control over what gets logged.

---

## 🧪 Development & Contributions

This plugin is currently in its early stages. Contributions are welcome! To contribute:

1. Fork the repository.
2. Clone your fork locally.
3. Create a new branch for your feature or fix.
4. Make your changes and commit them.
5. Push your changes to your fork.
6. Open a pull request with a clear description of your changes.

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/LVCHLANN/DiscordLogger/blob/main/LICENSE) file for details.

---

## 📞 Support

For issues or feature requests, please open an issue in the [Issues](https://github.com/LVCHLANN/DiscordLogger/issues) section of the repository.
