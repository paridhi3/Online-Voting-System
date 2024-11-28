# Online Voting System 🗳️👆

An **Online Voting System** built using Java, JSP, and Servlet, designed to automate the voting process. This system ensures secure, anonymous voting while providing a user-friendly interface and efficient vote management.

---

## 📄 **Abstract**

The Online Voting System simplifies the voting process by allowing users to securely cast their votes online. The system displays a login page, parties with their symbols, and options to choose and submit votes anonymously. It stores voter data and vote counts in a MySQL database, ensuring security and reliability. The project follows the MVC design pattern for scalability and maintainability.

---

## 🚀 **Features**

1. **User Authentication:** 
   - Secure login portal.  
   - Maintain voter anonymity during the voting process.

2. **Party Display**  
   - Show participating parties with their unique symbols.

3. **Voting Process**  
   - Allow users to select a party and cast votes securely.

4. **Vote Recording**  
   - Store anonymous votes and voter details in a MySQL database.

5. **Result Calculation**  
   - Automatically count votes and declare the winning party.

6. **User Interface**  
   - Responsive, user-friendly design using HTML, CSS, and Bootstrap.

7. **MVC Architecture**  
   - Modular code structure for easy maintenance and scalability.

8. **Security**  
   - Prevent duplicate voting and ensure data integrity.

9. **Admin Panel**  
   - Manage parties and monitor vote counts.

---

## 👥 **Roles in the System**

1. **Admin:**  
   - Login/Logout using a Security Key to access secure Admin panel.
   - Add/Delete party details.  
   - View election result.    
   - One admin only.

2. **Voter:**  
   - Register with unique credentials (e.g., voter ID, date of birth etc.).  
   - Login/Logout securely using their voter ID and password.  
   - Vote for their chosen party.
   - Cannot vote more than once.  
   - Must be 18 years or older. 

---

## 🛠 **Tech Stack**

- **Backend**: Java, JSP, Servlet  
- **Frontend**: HTML, CSS, JavaScript  
- **Database**: MySQL  
- **Design Pattern**: MVC Architecture  

---

## ⚙️ **Setup and Installation**

1. Clone the repository:  
   ```bash
   git clone https://github.com/paridhi3/OnlineVotingSystem.git
   cd OnlineVotingSystem
   ```
   
