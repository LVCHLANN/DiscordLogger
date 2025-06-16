# 🛠️ Troubleshooting Guide

Having trouble getting **DiscordLogger** to work properly? This guide covers the most common issues, along with solutions and helpful tips for resolving them.

---

## ⚠️ Nothing is showing in Discord

### ✅ Checklist

Make sure you’ve completed these steps:

- ✅ Plugin is in your `plugins/` folder.
- ✅ Server is restarted (not just reloaded).
- ✅ Webhook URL is valid and starts with `https://discord.com/api/webhooks/`.
- ✅ You're using **Java 17 or newer**.
- ✅ Logging options (e.g., `log-player-join`) are set to `true` in `config.yml`.

---

### 🔎 Still not working?

Here’s what to try next.

---

## 🔍 Check the Server Logs

Check your server console after startup. If there’s an issue loading the plugin, it will often be logged here.

Look for lines like:

```
[DiscordLogger] Failed to connect to webhook
```

Or:

```
[DiscordLogger] Could not parse config.yml
```

These indicate either a **bad webhook URL** or a **syntax issue in config.yml**.

---

## 🧪 Test the Webhook

Paste your webhook URL into a tool like:

- [Webhook Tester](https://webhook.site)
- [Discord Webhook Sender](https://discohook.org)

Send a test message to verify that the webhook is functional.

If the webhook doesn't work outside the plugin, it may have been **deleted** or **revoked**.

---

## 🧾 Validate Your config.yml

Sometimes, a small YAML error can break the plugin.

Copy your `config.yml` and paste it into a validator like:

🔗 [https://yamlchecker.com](https://yamlchecker.com)

Common problems:

- Using `:` inside strings without quotes.
- Misaligned indentation.
- Missing quotation marks on URLs.

---

## 🧱 Plugin Doesn’t Appear in /plugins

If the plugin doesn't show up in green when you type `/plugins`, it likely failed to load.

Possible causes:

- Incompatible Minecraft server type or version.
- Using Java 8 or 11 (Java 17+ is required).
- Corrupt `.jar` file — re-download from [Releases](https://github.com/LVCHLANN/DiscordLogger/releases).

---

## 🧪 I’ve Set Everything but It Still Doesn’t Work

Try this order of debugging:

1. **Check console logs** — any stack traces?
2. **Revalidate config** — look for YAML issues.
3. **Replace webhook URL** — test with a fresh one.
4. **Disable all other plugins** — test for conflicts.
5. **Use a clean server install** — minimal environment.

Still not working? [Open a GitHub issue](https://github.com/LVCHLANN/DiscordLogger/issues/new/choose) with:

- Your full `config.yml` (remove the webhook)
- The output of `/version` and `/plugins`
- Full error message (if available)

---

## 🔐 Protecting Your Webhook

Your webhook is sensitive. If leaked, anyone can send messages into your Discord server.

### To stay safe:

- ✅ NEVER post `config.yml` with the webhook included.
- ✅ Rotate the webhook regularly.
- ✅ Use IP whitelisting or bot-based alternatives if needed (planned).

---

## 🧪 Debug Mode (Coming Soon)

Future versions will include a debug mode with:

- Verbose console logs
- `/discordlogger test` command
- Webhook response diagnostics

Planned for **v1.1.0**

---

## 📤 Discord Messages Are Formatting Weird

DiscordLogger v1.0.4 sends messages as plain text only.

Weird formatting could be caused by:

- Using Discord-specific markdown (like `*italics*` or `__underline__`)
- Characters being misinterpreted by Discord

This will be addressed in **Webhook Embed Support** in upcoming versions. See [Webhook Formatting](webhook-formatting.md) for plans.

---

## ⚙️ My Server Uses a Custom Fork

Forks like Purpur, Airplane, or custom builds may cause unknown behavior.

While DiscordLogger supports Paper, Spigot, and Purpur officially, others may work **unofficially**. Use at your own risk, and test thoroughly.

---

## ❓Still Need Help?

- Search open [Issues](https://github.com/LVCHLANN/DiscordLogger/issues)
- Ask a question in [Discussions](https://github.com/LVCHLANN/DiscordLogger/discussions)
- Join our Discord (coming soon)

---

<center><sub>Thanks for using DiscordLogger — let's squash those bugs together!</sub></center>
