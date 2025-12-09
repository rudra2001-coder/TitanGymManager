# Titan Gym Manager

![Titan Gym Manager Banner](https://place-hold.it/1200x300?text=Titan+Gym+Manager&fontsize=72) 

**An offline-first, modern Android application designed to help gym owners manage their members, memberships, finances, and more—all from their device.**

---

## ✨ Features

Titan Gym Manager is a feature-rich application built to be completely offline, ensuring your data is always accessible.

### 🏋️ Member Management
- **Add, Update, & Delete Members:** Easily manage member profiles.
- **Detailed Profiles:** Store name, phone, gender, age, weight, height, address, and profile photo.
- **Smart Search:** Quickly find members by name, phone number, or ID.
- **Status Tracking:** View member status at a glance (Active, Expired, Due Payment, Upcoming Expiry).

### 💳 Membership & Payments
- **Custom Packages:** Create flexible membership packages (monthly, yearly, custom durations).
- **Payment Tracking:** Record full and partial payments with support for various methods (Cash, bKash, Nagad, Bank).
- **Payment History:** View a complete history of all payments, filterable by member and date.
- **Due Payment Alerts:** Instantly see a list of all members with outstanding dues.

### 📊 Dashboard & Analytics
- **At-a-Glance Summary:** Key metrics like total members, active vs. expired ratio, and monthly income.
- **Visual Graphs:**
  - Monthly New Member Graph.
  - Active vs. Expired Member Pie Chart.
  - Monthly Income Graph.

### 📅 Expiry & Reminders
- **Expiry Alerts:** Get notified for memberships expiring today, within 3 days, 7 days, or this month.
- **Instant Phone Call:** Tap on a member in the expiry list to directly dial their number.

### 💼 Expense & Equipment Management
- **Track Expenses:** Log gym expenses by category (Rent, Salary, etc.) and view monthly totals.
- **Manage Equipment:** Keep a record of all gym equipment, including purchase date, price, and condition.

### 👤 Trainer Management
- **Trainer Profiles:** Add and manage trainer information, including salary and joining date.

### 🧾 Comprehensive Reports
- **Financial Overview:** Generate reports for Income, Expenses, and Profit/Loss.
- **Filter by Period:** View reports daily, weekly, monthly, or yearly.

### ⚙️ Settings & Backup
- **Light & Dark Mode:** Choose your preferred app theme.
- **Local Backup & Restore:** Securely export and import your entire database to local storage, ensuring your data is never lost.

---

## 🛠️ Tech Stack & Architecture

This project is built with a modern Android tech stack, focusing on best practices and a clean, scalable architecture.

- **100% [Kotlin](https://kotlinlang.org/):** For concise, safe, and modern code.
- **[Jetpack Compose](https://developer.android.com/jetpack/compose):** For building a beautiful, declarative UI.
- **MVVM Architecture:** Separating business logic from the UI for a clean and maintainable codebase.
- **[Room Database](https://developer.android.com/training/data-storage/room):** For robust, offline-first local data storage.
- **[Hilt](https://dagger.dev/hilt/):** For dependency injection, making the code more modular and testable.
- **[Kotlin Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html):** For managing asynchronous operations smoothly.
- **[Jetpack Navigation](https://developer.android.com/guide/navigation):** For handling all in-app navigation.
- **[MPAndroidChart](https://github.com/PhilJay/MPAndroidChart):** For creating insightful charts and graphs.
- **[DataStore](https://developer.android.com/topic/libraries/architecture/datastore):** For storing user preferences like app theme.

### Architecture

The app follows the MVVM (Model-View-ViewModel) architecture with a repository pattern, ensuring a clear separation of concerns:

- **UI Layer (Compose):** Observes data from the ViewModel and renders the UI.
- **ViewModel Layer:** Holds and processes UI-related data, interacting with the repository.
- **Repository Layer:** Acts as a single source of truth for all data, abstracting the data source (Room).
- **Data Layer (Room):** Provides a robust local database for all application data.

---

## 🚀 How to Build

To build and run the project, follow these steps:

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/your-username/titan-gym-manager.git
    ```
2.  **Open in Android Studio:**
    - Open Android Studio (latest stable version recommended).
    - Click on `File` > `Open` and select the cloned project directory.
3.  **Build and Run:**
    - Let Gradle sync all the dependencies.
    - Click the `Run` button to build and install the app on an emulator or a physical device.

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

```
This project is open source and you are welcome to contribute!
```
