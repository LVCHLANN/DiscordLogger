# ❓ Frequently Asked Questions (FAQ)

This page answers common questions about **DiscordLogger**, including setup, errors, and feature behavior.

---

## 🔧 General Questions

### 📌 What does DiscordLogger do?

**DiscordLogger** is a Minecraft plugin that sends server activity events to a Discord webhook. These events can include player joins, chat messages, block actions, and more.

---

### ⚙️ How do I install the plugin?

1. Download the `.jar` file from the [Releases page](https://github.com/LVCHLANN/DiscordLogger/releases).
2. Drop it in your `plugins/` folder.
3. Restart the server.

For full steps, visit [Getting Started](getting-started.md).

---

### 📬 How do I get a Discord Webhook?

You can create one in Discord:

1. Open your Discord server settings.
2. Go to **Integrations > Webhooks**.
3. Click **New Webhook**, name it, and copy the URL.

Paste it into your config like this:

```yaml
webhook-url: "https://discord.com/api/webhooks/..."
```

---

### 📝 Why isn't anything showing up in Discord?

Check the following:

- The webhook URL is valid and not expired.
- The plugin loaded successfully (`/plugins` should list it).
- The event is enabled in `config.yml`.
- Your server is running Java 17+.
- Your console shows no startup errors.

If all seems correct, visit [Troubleshooting](troubleshooting.md).

---

## 🖥️ Configuration Questions

### 🔁 How do I reload the config?

Currently, you must restart the server to apply config changes.

A `/discordlogger reload` command is planned for **v1.1.0**.

---

### 🎛️ Can I disable specific logs?

Yes! In your config:

```yaml
log-player-join: false
log-player-quit: true
```

Set each event to `true` or `false` to enable or disable it.

Full list of logging options can be found [here](logging-options.md).

---

### 💬 Can I format the messages?

Not yet — but future versions will support:

- Discord embeds
- Custom message templates
- Per-event formatting

See [Webhook Formatting](webhook-formatting.md) and [Planned Features](planned-features.md) for more info.

---

## 🧪 Debugging & Errors

### ⚠️ The plugin crashes or throws errors — what now?

1. Check your config syntax using a [YAML validator](https://yamlchecker.com/).
2. Confirm your server runs Java 17+.
3. Review logs for stack traces or exceptions.

If the issue is caused by the plugin and not user error, open an issue with:

- Your full `config.yml`
- Console output or error logs
- Minecraft and plugin version

---

### 🔒 Is it safe to post my config?

✅ Yes, as long as you **remove or redact** your Discord webhook URL.

Never share your webhook publicly. Anyone with it can send messages to your channel.

---

## 🧩 Feature & Contribution Questions

### 📜 Can I suggest a feature?

Absolutely! Visit the [Discussions page](https://github.com/LVCHLANN/DiscordLogger/discussions) or [open an issue](https://github.com/LVCHLANN/DiscordLogger/issues/new/choose).

---

### 🤝 Can I contribute code?

Yes, contributions are welcome! Read the [Contributing Guide](../.github/CONTRIBUTING.md) for setup instructions.

---

### 🧪 Is there a way to test if my webhook works?

This is planned for **v1.1.0** via the command:

```bash
/discordlogger test
```

It will send a test message to your configured webhook.

---

### 💡 Will this work with other plugins?

DiscordLogger is designed to be lightweight and non-invasive. It should work fine alongside plugins like EssentialsX, LuckPerms, or CoreProtect.

Compatibility logging (e.g., commands from Essentials) may be expanded in future updates.

---

### 🖼️ Do you support embeds?

Plain text is used in **v1.0.4**. Embed support is in development for **v1.1.0**, including:

- Colors
- Custom icons
- Message titles/descriptions
- Server name and timestamp

Visit [Webhook Formatting](webhook-formatting.md) for details.

---

## 🔮 Looking Ahead

Stay up to date with upcoming features on the [Planned Features](planned-features.md) page.

If your question isn’t listed, feel free to:

- 🧵 Start a [Discussion](https://github.com/LVCHLANN/DiscordLogger/discussions)
- 🐞 [Report a Bug](https://github.com/LVCHLANN/DiscordLogger/issues/new/choose)
- 💬 Join our Discord (coming soon)

---

<center><sub>Thanks for using DiscordLogger! Your support drives future updates.</sub></center>
