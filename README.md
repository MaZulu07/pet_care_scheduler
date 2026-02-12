\# 🐾 PetCareScheduler



!\[Java](https://img.shields.io/badge/Language-Java-blue?logo=java)

!\[Platform](https://img.shields.io/badge/Platform-Console-lightgrey)

!\[License](https://img.shields.io/badge/License-MIT-green)



---



\## 📌 Project Overview

\*\*PetCareScheduler\*\* is a \*\*Java console application\*\* that helps pet owners and clinic staff manage pets and their appointments. The system allows:



\- Registering pets with details  

\- Scheduling appointments (Vet visit, Vaccination, Grooming)  

\- Displaying pet records and appointment history  

\- Generating reports for upcoming and overdue care  



The application ensures \*\*data validation\*\*, \*\*error handling\*\*, and \*\*persistent storage\*\* between sessions.



---



\## ✨ Features



\### 🐶 Pet Management

\- Register pets with: ID, Name, Species/Breed, Age, Owner Name, Contact Info, and Registration Date  

\- View all registered pets  



\### 📅 Appointment Scheduling

\- Schedule future appointments with optional notes  

\- Validates appointment type and future date/time  

\- Prevents scheduling for non-existent pets  



\### 📊 Records \& Reports

\- Display:

&nbsp; - All registered pets  

&nbsp; - Appointments for a specific pet  

&nbsp; - Upcoming appointments for all pets  

&nbsp; - Past appointment history  

\- Generate reports:

&nbsp; - Pets with appointments in the next 7 days  

&nbsp; - Pets overdue for a vet visit (no visit in last 6 months)



\### 💾 Data Persistence

\- Load and save pets and appointments to files  

\- Ensures data is retained between sessions



---



\## 🛠️ Technologies

\- \*\*Java 17+\*\*  

\- Console Input/Output (`Scanner`)  

\- Collections (`List`, `Map`)  

\- Date \& Time API (`LocalDate`, `LocalDateTime`, `DateTimeFormatter`)  

\- File I/O (`File`, `PrintWriter`, `Scanner`)  



---



\## 🚀 Installation \& Usage



1\. \*\*Clone the repository\*\*



```bash

git clone <repository-url>

cd PetCareScheduler

```



2\. \*\*Compile the Java files\*\*



```bash

javac \*.java
```



