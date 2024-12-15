# 🌊 H2OMG - Water Quality Control System  

**AquaSense** is an IoT-based solution designed to monitor and control water quality in real-time. This Android application leverages Firebase to provide users with accurate, up-to-date data on pH levels and conductivity of water, ensuring safety and reliability. The system is designed for environmental monitoring, agriculture, and industrial applications where water quality is critical.  

---

## 🚀 Features  
- **Real-Time Monitoring:** Displays live data on pH levels and conductivity fetched from Firebase.  
- **User-Friendly Interface:** Intuitive Android app developed using **XML** and **Java** for seamless user experience.  
- **IoT Integration:** Data collected from sensors (pH and conductivity) via ESP32 is processed and stored in Firebase.  
- **Customizable Alerts:** Potential for future implementation of alerts for unsafe water conditions.  

---

## 🛠️ Technologies Used  

### **Hardware**  
- **ESP32:** Collects data from sensors and sends it to Firebase.  
- **pH Sensor (E-201):** Measures the pH level of water.  
- **Conductivity Sensor:** Measures water's electrical conductivity.  

### **Software**  
- **Android Studio**: Development environment for the mobile app.  
- **Firebase Realtime Database**: Stores sensor data in real-time.  
- **Java & XML**: Core technologies for Android app development.  

---

## 📱 Application Screens  

1. **Home Screen:** Overview of real-time water quality data (pH and conductivity).  
2. **Detailed View:** Graphs and logs of historical data fetched from Firebase.  
3. **Settings:** Options to customize thresholds for water quality parameters (future development).  

---

## 🛠️ System Architecture  

1. **Sensors:** Collect data on water quality parameters.  
2. **ESP32:** Sends collected data to Firebase via Wi-Fi.  
3. **Firebase:** Acts as a bridge between the IoT hardware and the mobile app.  
4. **Android App:** Retrieves and displays data in real-time using a clean, user-friendly interface.  

---

## 💻 How to Run  

### Prerequisites:  
- Android Studio installed on your machine.  
- Firebase Realtime Database set up with your project.  
- ESP32 with pH and conductivity sensors configured.  

