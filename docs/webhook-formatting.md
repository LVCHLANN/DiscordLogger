# 🎨 Webhook Formatting Guide

DiscordLogger currently sends all messages as **plain text**, but upcoming versions will support rich **Discord Embeds**. This guide explains how messages are formatted, what can be customized, and what improvements are planned.

---

## ✅ Current Formatting (v1.0.4)

In the current release, all logs are sent as raw plain text to your Discord webhook. Here's what that looks like for each type of event:

| Event Type        | Example Message                                     |
|-------------------|-----------------------------------------------------|
| Player Join       | `[Survival] Player Join: Lachlan has joined.`       |
| Block Place       | `[Creative] Block Placed: Lachlan placed STONE.`    |
| Server Command    | `[Server] Ran command: /stop`                        |
| Player Death      | `[Hardcore] Death: Lachlan was slain by Zombie.`    |

These messages are simple and readable but lack structure or rich presentation.

---

## 🧱 How It Works

Messages are formatted using Markdown-like string building inside Java. Each message includes:

- The **server name** prefix (from `config.yml`)
- The **event type**
- The **player/entity name** if relevant
- The **action** or **message content**

There is currently no support for color, bold, italics, or Discord embeds in v1.0.4.

---

## 🔮 Planned Embed Formatting (Future Versions)

In upcoming versions, DiscordLogger will support full **Discord Embed** messages for better readability, structure, and customization.

Here’s an example of how an embedded message might look:

```json
{
  "embeds": [
    {
      "title": "📥 Player Joined",
      "description": "**Lachlan** joined the server.",
      "color": 5763719,
      "footer": {
        "text": "Server: Survival"
      },
      "timestamp": "2025-06-16T03:20:00Z"
    }
  ]
}
```

---

## 🧰 Planned Embed Features

| Feature                       | Description                                                                 |
|------------------------------|-----------------------------------------------------------------------------|
| Custom titles                | Choose your own embed title per event type                                 |
| Timestamping                 | Uses ISO 8601 timestamps for Discord to display event time                  |
| Color coding                 | Different colors for join, quit, death, commands, etc.                      |
| Icons / Emojis               | Adds visual indicators to embed titles                                      |
| Markdown support             | Rich text support in `description` fields                                  |
| Optional compact/verbose modes | Toggle between concise or detailed embed formats                        |

---

## 🧪 Custom Embed Builder (Future)

We plan to support custom embed layout using the config file, allowing something like:

```yaml
use-embeds: true

embeds:
  player-join:
    title: "📥 Player Joined"
    description: "**%player%** joined the game."
    color: "#00ff00"
    footer: "Server: %server%"
    timestamp: true
```

This allows total control over how each message type appears in Discord.

---

## 📏 Color Reference (Decimal)

Discord uses **integer RGB values** for embed colors. Some example colors:

| Color      | Hex       | Decimal     |
|------------|-----------|-------------|
| Green      | `#00ff00` | `65280`     |
| Red        | `#ff0000` | `16711680`  |
| Yellow     | `#ffff00` | `16776960`  |
| Blue       | `#0000ff` | `255`       |
| Purple     | `#aa00ff` | `11141247`  |

You can calculate this using online RGB-to-decimal converters.

---

## 🧠 Tips

- Emojis help distinguish messages in busy logs (`📥`, `⚠️`, `🛑`, etc.)
- Avoid overly long descriptions in embeds (Discord has limits).
- Use different colors for different types of logs for quick scanning.

---

## 🛣️ What’s Next?

Check out:

- 📋 [Logging Options](logging-options.md)
- 🛠️ [Planned Features](planned-features.md)
- ❓ [FAQ](faq.md)

---

<center><sub>Embed support is coming soon — stay tuned for v1.1.0!</sub></center>
