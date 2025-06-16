# ⚙️ Configuration Guide

Welcome to the **DiscordLogger Configuration Guide**!  
This page explains each configurable option in `config.yml`, what it does, and why you might want it.

---

## 📄 Location

After launching the server with the plugin installed, a configuration file is generated at:

`/plugins/DiscordLogger/config.yml`

---

## 🔑 Basic Settings

### • `server-name`  
The name that appears in log messages. Useful for distinguishing between multiple servers (e.g., Survival, Creative).

```yaml
server-name: "Survival"
```

---

### • `webhook-url`  
Your Discord webhook URL. All messages are sent to this webhook.  
**Important:** Keep this private.

```yaml
webhook-url: "https://discord.com/api/webhooks/your-webhook-id"
```

---

## 🎛️ Logging Categories

Each section of the config file contains event toggles. Set each one to `true` to enable or `false` to disable.

---

### 👤 `player-events`

Controls logging of player activity.

```yaml
log-player-join: true
log-player-quit: true
log-player-chat: true
log-player-death: true
```

_Planned for future updates:_

```yaml
log-player-kick: false
log-player-ban: false
log-player-advancement: false
log-player-command: false
```

---

### 🧱 `block-and-world-events`

Logs when the world changes due to player or environmental actions.

```yaml
log-block-break: false
log-block-place: false
```

_Planned for future updates:_

```yaml
log-sign-change: false
log-container-open: false
log-world-save: false
log-weather-change: false
log-explosion: false
```

---

### 🐮 `entity-events`

Covers mobs and other non-player entities.

```yaml
# (Currently no live options)
```

_Planned for future updates:_

```yaml
log-entity-spawn: false
log-entity-death: false
log-entity-damage: false
log-entity-breed: false
```

---

### ⚙️ `server-admin-events`

Covers server operations and plugin events.

```yaml
log-server-command: false
```

_Planned for future updates:_

```yaml
log-plugin-load: false
log-plugin-enable: false
log-plugin-disable: false
log-server-start: false
log-server-stop: false
```

---

### 📦 `inventory-events` *(Planned)*

Handles user inventory actions (coming soon).

```yaml
log-item-drop: false
log-item-pickup: false
log-item-consume: false
log-inventory-click: false
```

---

### 🧪 `custom-events` *(Planned)*

Advanced or niche logging features — ideal for staff or audit tools.

```yaml
log-afk-status-change: false
log-player-op-deop: false
log-resource-pack-status: false
```

---

## 🛠️ Tips

- ✅ You can reload the plugin via `/discordlogger reload` *(planned)*.
- 🧪 If a setting isn't doing anything yet, it's likely planned for a future update.
- 🧼 Keep your config clean and formatted to avoid YAML syntax errors.

---

## 🔍 Need Help?

See the [Troubleshooting Guide](troubleshooting.md) for tips on fixing common problems.

---

<center><sub>Last updated for DiscordLogger v1.0.4</sub></center>
