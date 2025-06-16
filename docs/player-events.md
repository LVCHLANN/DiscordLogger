# 🧍 Player Events

This category includes all events related directly to player activity, such as joining, chatting, and dying.

---

## ✅ Available in v1.0.4

### `log-player-join`
Logs a message when a player joins the server.

**Why enable it?**
- Keep track of who is connecting to your server
- Useful for activity monitoring or welcoming players

**Example Output:**
```
[16-06-2025 14:30:12] Player Join: [Survival] Steve
```

---

### `log-player-quit`
Logs a message when a player disconnects from the server.

**Why enable it?**
- Useful for monitoring player session lengths
- Can indicate ragequits or disconnections

**Example Output:**
```
[16-06-2025 14:42:57] Player Quit: [Survival] Steve
```

---

### `log-player-chat`
Logs all public chat messages sent by players.

**Why enable it?**
- Allows remote monitoring of conversations
- Useful for moderation or content review

**Example Output:**
```
[16-06-2025 14:34:10] Player Chat: [Creative] Alex: Hello!
```

---

### `log-player-death`
Logs when a player dies, including cause of death.

**Why enable it?**
- Fun for survival communities
- Helpful for identifying common causes of death (lava, mobs, etc.)

**Example Output:**
```
[16-06-2025 14:36:44] Player Death: [Survival] Steve was slain by Zombie
```

---

## 📝 Planned Events (Not yet implemented)

These events are planned for future versions and are not available in v1.0.4:

| Event                        | Description                                     |
|-----------------------------|-------------------------------------------------|
| `log-player-command`        | Logs commands executed by players              |
| `log-player-teleport`       | Logs teleportation events                      |
| `log-player-kick`           | Logs when a player is kicked                   |
| `log-player-ban`            | Logs when a player is banned                   |
| `log-player-advancement`    | Logs when a player earns an advancement        |
| `log-player-respawn`        | Logs when a player respawns after death        |
| `log-player-item-consume`   | Logs when a player consumes an item            |
| `log-player-interact`       | Logs interactions with entities or blocks      |
| `log-player-drop-item`      | Logs when a player drops an item               |
| `log-player-pickup-item`    | Logs when a player picks up an item            |

---

> 📌 For current status of planned features, see [README.md](../README.md#planned-features)
