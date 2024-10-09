# YouTube Simulation Application

This Java project simulates a simple YouTube-like application using the **Observer** design pattern. The application allows users to create channels, upload videos, and manage subscriptions. Users can subscribe to channels and receive notifications when new videos are uploaded.

## Table of Contents
- [YouTube Simulation Application](#youtube-simulation-application)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Design Pattern](#design-pattern)
  - [Classes Overview](#classes-overview)
    - [`Canal`](#canal)
    - [`Usuario`](#usuario)
    - [`YoutubeApp`](#youtubeapp)
  - [How to Run](#how-to-run)

## Features
- **Add Users**: Create new users who can subscribe to channels.
- **Add Channels**: Create new channels that can upload videos.
- **Upload Videos**: Channels can upload videos, which notify all subscribers.
- **Subscribe to Channels**: Users can subscribe to their favorite channels to get notified of new videos.
- **Unsubscribe from Channels**: Users can unsubscribe from channels if they no longer want to receive notifications.
- **Observer Design Pattern**: The relationship between channels and users is modeled using the Observer pattern.

## Design Pattern

The **Observer Pattern** is a behavioral design pattern where an object (the **Subject**) maintains a list of its dependents (the **Observers**) and notifies them automatically of any state changes. In this project:
- The **Canal** class acts as the subject (YouTube channel).
- The **Usuario** class acts as the observer (YouTube user).
- When a channel uploads a new video, all subscribed users are notified of the update.

## Classes Overview

### `Canal`
- **Role**: Acts as the Subject.
- **Functionality**: Manages subscribers, allows video uploads, and notifies subscribers when a new video is posted.
- **Key Methods**:
  - `subirVideo(String title)`: Uploads a new video and notifies subscribers.
  - `agregarSuscriptor(IObserver subscriber)`: Adds a new subscriber to the channel.
  - `quitarSuscriptor(IObserver subscriber)`: Removes a subscriber from the channel.
  - `notificarUsuarios()`: Notifies all subscribers about a new video.

### `Usuario`
- **Role**: Acts as the Observer.
- **Functionality**: Receives updates from subscribed channels and can subscribe/unsubscribe to channels.
- **Key Methods**:
  - `update(String videoTitle, String channelName)`: Receives a notification when a new video is uploaded.
  - `suscribirse(Canal channel)`: Subscribes to a channel.
  - `desuscribirse(Canal channel)`: Unsubscribes from a channel.

### `YoutubeApp`
- **Main class**: Provides a menu-driven interface to interact with the system.
- **Key Functionalities**:
  - Adding users and channels.
  - Uploading videos to channels.
  - Subscribing and unsubscribing users from channels.

## How to Run

1. Clone this repository or download the project files.
2. Compile and run the `YoutubeApp.java` file.
3. Follow the on-screen menu to interact with the application.

To compile and run from the command line:

```bash
javac -d bin -sourcepath src src/YoutubeApp.java
java -cp bin YoutubeApp
