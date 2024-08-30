<h1> ColorApp </h1>

<p> ColorApp is a color list application built using Kotlin, Jetpack Compose, and MVVM architecture. It allows users to manage and sync a list of colors with offline capabilities and cloud synchronization. </p>

<h2> Features </h2>
<h4> Color List Display: View a list of colors on the main screen. Figma design </h4>
<h4> MVVM Architecture: The app follows the Model-View-ViewModel (MVVM) architecture pattern.</h4>
<h4> Cloud Database: Uses Firebase Database for cloud storage.</h4>
<h4> Offline Mode: Colors are stored locally using RoomDB, allowing the list to be displayed even without internet connectivity. </h4>
<h4> Add Color: Generate and store a new random color with parameters { color: "#AABBFF", time: 1683798291864 } in RoomDB when the "Add Color" button is clicked. </h4>
<h4> Sync Colors: Send color entries from local RoomDB to the cloud database when the "Sync" button is clicked. </h4>
<h4> Pending Sync Count: Displays the number of pending entries next to the sync icon, updated every time data is sent to the server. </h4>
<h4> Jetpack Compose: Utilizes Jetpack Compose for building the UI with themes and styles. </h4>

https://github.com/user-attachments/assets/1a64611e-2e84-4358-8745-1ea31b2522c9
