# 📋 Logging Options

DiscordLogger provides fine-grained control over what gets logged from your Minecraft server to your Discord channel. This document outlines each available logging toggle, its purpose, and why you might want to enable or disable it.

---

## 📁 Config Path

All logging options are found inside:

`/plugins/DiscordLogger/config.yml`

Each section is grouped for readability and ease of management.

---

## 👤 Player Events

Logs events triggered by players on the server.

| Config Option                 | Description                                                                 |
|------------------------------|-----------------------------------------------------------------------------|
| `log-player-join`            | Logs when a player joins the server.                                       |
| `log-player-quit`            | Logs when a player leaves the server.                                      |
| `log-player-chat`            | Logs player chat messages (supports Markdown formatting).                  |
| `log-player-death`           | Logs when a player dies, including cause of death.                         |
| `log-player-command`         | Logs commands executed by players.                                         |
| `log-player-teleport`        | Logs teleportation events (via commands or plugins).                       |
| `log-player-kick`            | Logs when a player is kicked, with reason if available.                    |
| `log-player-ban`             | Logs when a player is banned.                                              |
| `log-player-advancement`     | Logs when players earn advancements.                                       |
| `log-player-item-consume`    | Logs when a player eats or drinks an item.                                 |
| `log-player-interact`        | Logs right/left-click interactions with blocks or entities.                |
| `log-player-drop-item`       | Logs when a player drops an item.                                          |
| `log-player-pickup-item`     | Logs when a player picks up an item.                                       |
| `log-player-respawn`         | Logs when a player respawns.                                               |

---

## 🧱 Block & World Events

Useful for tracking world changes and performance diagnostics.

| Config Option                 | Description                                                                 |
|------------------------------|-----------------------------------------------------------------------------|
| `log-block-break`            | Logs when blocks are broken.                                               |
| `log-block-place`            | Logs when blocks are placed.                                               |
| `log-block-burn`             | Logs block fire burn events.                                               |
| `log-block-explode`          | Logs block explosions.                                                     |
| `log-block-grow`             | Logs crop growth and sapling/tree growth.                                  |
| `log-block-ignite`           | Logs block ignition events.                                                |
| `log-chunk-load`             | Logs when chunks are loaded.                                               |
| `log-chunk-unload`           | Logs when chunks are unloaded.                                             |
| `log-world-save`             | Logs when the world is saved.                                              |

---

## 🐮 Entity Events

Covers mobs, animals, and other entities.

| Config Option                     | Description                                                              |
|----------------------------------|--------------------------------------------------------------------------|
| `log-entity-death`               | Logs entity deaths (e.g. mobs, armor stands, etc.).                      |
| `log-entity-damage-by-entity`    | Logs entity damage caused by another entity.                            |
| `log-entity-spawn`               | Logs when entities spawn naturally or via spawners.                     |
| `log-creature-spawn`             | Logs specific creature spawn reasons.                                   |

---

## 🛠️ Server & Admin Events

Advanced server-level events for server operators and diagnostics.

| Config Option                 | Description                                                                 |
|------------------------------|-----------------------------------------------------------------------------|
| `log-server-command`         | Logs commands run from the console.                                        |
| `log-remote-server-command`  | Logs commands run from RCON or remote sources.                             |
| `log-server-ping`            | Logs when someone pings the server (e.g. in server list).                  |
| `log-plugin-enable`          | Logs when a plugin is enabled.                                             |
| `log-plugin-disable`         | Logs when a plugin is disabled.                                            |
| `log-server-load`            | Logs once the server has fully loaded.                                     |

---

## 🎒 Inventory Events

Track interactions with inventories and trading.

| Config Option                 | Description                                                                 |
|------------------------------|-----------------------------------------------------------------------------|
| `log-inventory-click`        | Logs when a player clicks in an inventory (e.g. chests, anvils).           |
| `log-inventory-open`         | Logs when a player opens an inventory.                                     |
| `log-trade-select`           | Logs when a player selects a trade with a villager.                        |

---

## 🧪 Custom Events

Events that are either plugin-based or require custom logic to detect.

| Config Option                 | Description                                                                 |
|------------------------------|-----------------------------------------------------------------------------|
| `log-keyword-alerts`         | Logs when certain configured keywords are said in chat.                    |
| `log-suspicious-commands`    | Detects and logs potentially dangerous or unauthorized commands.           |
| `log-new-ip-login`           | Logs when a player logs in from a new IP address.                          |
| `log-afk-detection`          | Logs when a player goes AFK or returns.                                    |
| `log-staff-mode-toggle`      | Logs when staff enter or leave staff/admin mode.                           |

---

## 🧩 Example Configuration

```yaml
server-name: "MyServer"
webhook-url: "https://discord.com/api/webhooks/your-webhook-id"

player-events:
  log-player-join: true
  log-player-quit: true
  log-player-chat: true

block-and-world-events:
  log-block-break: false
  log-block-place: false

entity-events:
  log-entity-death: false

server-admin-events:
  log-server-command: false

inventory-events:
  log-inventory-click: false

custom-events:
  log-keyword-alerts: false
```

---

## 🧠 Tips

- Keep your logs clean — don’t enable everything unless you need it.
- Use different Discord webhooks for different server types or environments (e.g. survival vs. creative).
- Some events can generate **a lot** of messages — test before enabling them in production.

---

## 🛣️ What’s Next?

Check out these related pages:

- 🎨 [Webhook Formatting](webhook-formatting.md)
- 🛠️ [Planned Features](planned-features.md)
- 🧠 [FAQ](faq.md)

---

<center><sub>Need help deciding what to enable? Ask the community or check the FAQ!</sub></center>
