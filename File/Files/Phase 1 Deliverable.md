#### **VATTO Tech – Phase 1**

**1\) Project Overview**

| Name: | VATTO Tech |
| :---- | :---- |
| **Members:** | Vithunan Ravichandran, Tony Ngo, Omar Ahmed, Tisha Patel, and Abdullah Mobashar  |
| **Roles**: | Project Manager: Vithunan RavichandranTechnical Manager: Tisha PatelFront-End Lead: Omar AhmedBack-End Lead: Abdullah Mobashar Software Quality Lead: Tony Ngo  Developers: All |

**Summary of the project’s purpose and scope:**  
We are developing a catalog application consisting of a front and back end supported by a database. The application will manage and display a collection of toys and games, serving as an inventory tracking system. The core functionality is that the application will provide a stock list and allow users to manage inventory efficiently. This project aims to digitize the tracking process of managing a large inventory of toys and games. This application will improve efficiency, reduce errors, and provide a more reliable and structured way to manage the stock.  
**High-level goals and expected outcomes:**  
This project aims to develop a fully functional command-line interface for managing the inventory or stock of a store. The application will include key features requested by the client, such as a stock listing, search capabilities, sorting and filtering options, and the ability to add, edit, or remove products. A secure login system will be implemented so users can easily and safely access their private accounts. Additionally, the catalog will be updated weekly, ensuring the stock is current and up-to-date.

#### **2)Team Agreements and Elicitation Documentation**

**Summary of the Team’s Contract**

VATTO Tech’s team contract outlines the roles and responsibilities of its members, including the following: Project Manager (Vithunan Ravichandran), Technical Manager (Tisha Patel), Front-end Lead (Omar Ahmed), Back-end Lead (Abdullah Mobasher), and Software Quality Lead (Tony Ngo). The team plans to maintain clear and consistent communication through a Discord server to which all members have access. Responses through this Discord channel must be expected within 24 hours. Weekly in-person meetings will be held outside lab meetings on Mondays at 12:40 pm in the Energy Research Centre building. Additionally, online virtual meetings will be held for specific tasks that must be completed. VATTO Tech also values accountability and encourages aiding others when someone is falling behind, that way, things are always kept on track.

Link to our official Team Contract \- [Lab 01: Software Development Team Contract \- Google Docs](https://docs.google.com/document/d/1KrSo5JykJSmi6zhaJy1sv6kDVKNVGgmWtrsAU2awmMU/edit?tab=t.0)

**Elicitation Methods Used**

* Surveys  
* Domain Research

**Research Key Findings**

The toy and game industry is experiencing steady growth, with digital games leading due to technological advancements and the increasing popularity of online entertainment. Due to social gaming trends and specialized groups, board games are also experiencing a significant comeback due to ongoing interest in collecting and playing centered on learning. Action figures and instructional toys are still growing in popularity. With devoted player bases, trading card games are expanding at a moderate clip, whereas puzzles are expanding at the slowest rate, probably because their market is more established. The market is growing and changing, with traditional and digital media advancing at varying speeds.

**Meeting notes:**

**What do you want the app to do?**  
“I want a command-line interface as a text or GUI that can manage and maintain a list of stock, I want to digitize my inventory tracking system. ”   
**What essential features do you require from the application?**  
“I want to be able to add, remove, and edit the current products I have, I also want a search function added. “  
**Who are the primary users of the app, whether it be for inventory purposes or a store site?**  
“This app will only be used for inventory purposes only, and will not be a store site.”  
**How frequently does the data in the catalog need to be updated?**  
“The data in the catalog will need to be updated about once a week.”  
**What level of customization does the user expect?**  
“I want a sort/filter feature added to the search function. A key sort filter I want is sort by relevance.”  
**Do you want a login feature where users require a username and password?**  
“Yes, I do want a login feature for people to have protected accounts.”

**3\) User Stories and Prioritization**   
**User Stories and Justification for Prioritization Decisions:**

**Search Function (Priority 10\)**  
“As an inventory manager, I want a search function to search for specific items with the available filtering by relevance, name, filtering from cheapest to highest in price.”

Users must be able to find products efficiently. A store without search functionality frustrates customers and leads to poor user experience.

**Add, Remove, or Edit Products to the Database (Priority 10\)**  
“As an Inventory manager, I want to be able to add, edit, or remove products in my inventory to keep the catalog up to date.”

This is the highest priority because the system needs a way to store and manage products. 

**Overview of Inventory (Priority 10\)**  
“As an Inventory Manager, I want to view a real-time overview of the store’s inventory So that I can monitor stock levels, identify low-stock items, and manage restocking efficiently.”

 Essential for tracking stock levels. Ensuring that products are visible and available is a foundation for the rest of the system.

**Login** **(Priority 30\)**  
“As an admin, I want a login feature so that users can have a secure account and buy games or toys from my inventory.

It is important for security but not critical for a store's inventory catalog.

**Adding to Cart (Priority 40\)**  
"As a customer, I want to add multiple items to my shopping cart to purchase them all at once."

It is a fundamental feature for a store but is only needed when the franchise would like to implement a store feature.

**Receive Stock notifications (Priority 50\)**   
“As a store owner, I want to receive notifications when a game’s stock is below a threshold so I can restock in time.”

Useful for inventory convenience but not essential in the early stages.

**Customer Reviews (Priority 50\)**  
“As a customer, I want to be able to write reviews of products.”

While reviews help build trust and influence purchasing decisions, they are not essential for the initial functionality of the system.

**Sales Report (Priority 50\)**  
“I want to track the best-selling products to optimize restocking decisions and recommend the most popular games.”

Helps with business insights but does not impact the user’s ability to browse items.

**Inventory Purchasing (Priority 50\)**  
“As a store employee, I want to mark items as sold (removing from inventory) when processing transactions.”

It is important for business operations but not a priority for the initial catalog experience.

**Updating inventory weekly (Priority 50\)**  
“As a store employee, I want to be able to update the inventory weekly so inventory stays accurate

Keeping inventory up to date is important for accuracy, but it does not directly impact the initial functionality of searching and browsing.

**Prioritizing User Stories:**

**MUST Have:** Search function, Add, Remove, Edit products to the database, Sort/FilterProducts

**SHOULD Have:** Update data every Week, Login System

**NICE TO Have:** Toy Recommendations, Receive stock notifications, Customer Reviews, Inventory Profile with History of Products Purchased, Adding to Cart

**4\) Effort Estimation & Iteration Planning**

**Planning Poker**

**(SOLO)**

| Feature | Omar | Vithunan | Tony | Abdullah | Tisha | Consensus | Priority |
| :---- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
| Login Feature | 24 hours | 24  hours | 48 hours | 48 hours | 48 hours |  | 30 |
| Add/Edit/Remove from Database  | 96 hours | 96 hours | 120 hours | 120 hours | 96 hours |   | 10 |
| Search Function | 24 hours | 24 hours | 48 hours | 48 hours | 48 Hours |  | 10 |
| Sort/Filter Products | 96 hours | 96 hours | 72 hours | 120 hours | 96 hours |  | 20 |
| Adding to Cart | 24 hours | 24 hour | 48 hours | 48 hours |  24 hours |  | 40 |
| Inventory Purchasing | 48 hours | 72 hours | 48 hours | 24 hour | 48 Hours |  | 50 |
| Customer Reviews | 72 hours | 72 hours | 96 hours | 72 hours | 96 hours |  | 50 |
| Stock Notifications | 48 hours | 24 hour | 48 hours | 72 hours | 48 hours |  | 50 |
| Sales Report | 48 hours | 24 hours | 48 hours | 48 hours | 48 hours |  | 50 |
| Overview of Inventory | 48 hours | 24 hours  | 48 hours | 48 hours | 48 hours |  | 10 |
| Create Database | 96 hours | 72 hours | 84 hours | 72 hours | 72 hours |  | 20 |
| User Interface | 24 hours | 48 hours | 72 hours | 24 hours | 48 hours |  | 20 |

**(GROUP)**

| Feature | Omar | Vithunan | Tony | Abdullah | Tisha | Consensus | Priority |
| :---- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
| Login Feature | 48 hours | 24 hours | 48 hours | 48 hours | 24 hours | 48 hours | 30 |
| Add/Edit/Remove from Database  | 96 hours | 96 hours | 120 hours | 120 hours | 96 hours |  96 hours | 10 |
| Search Function | 48 hours | 24 hours | 48 hours | 48 hours | 72 hours | 48 hours | 10 |
| Sort/Filter Products | 96 hours | 96 hours | 96 hours | 120 hours | 96 hours | 96 hours | 20 |
| Adding to Cart | 24 hours | 48 hours | 24 hours | 24 hours | 24 hours | 24 hours | 40  |
| Inventory Purchasing | 48 hours | 24 hours | 48 hours | 48 hours | 48 hours | 48 hours | 50 |
| Customer Reviews | 72 hours | 96 hours | 72 hours | 96 hours | 72 hours | 72 hours | 50 |
| Stock Notifications | 48 hours | 48 hour | 48 hours | 72 hours | 48 hours | 48 hours | 50 |
| Sales Report | 72 hours | 72 hours | 72 hours | 72 hours | 96 hours | 72 hours | 50 |
| Overview of Inventory | 48 hours | 24 hours | 48 hours | 48 hours | 48 hours | 48 hours | 10 |
| Create Database | 72 hours | 72 hours | 72 hours | 48 hours | 72 hours | 72 hours | 20 |
| User Interface | 24 hours | 24 hours | 24 hours | 48 hours | 24 hours | 24 hours | 20 |

**Iteration Planning and Task Assignments:**

**Iteration 1: Core Functionality( 10 days)		 Assigning Tasks**  
Create Database **(3 Days )				\-** Abdullah Mobashar  
Add/Edit/Remove from Database  **(4 Days)		\-** Vithunan Ravichandran and Tisha Patel  
Search Function **(2 Days) 				\-** Tony Ngo  
User interface **(1 Day)				\-** Omar Ahmed

**Iteration 2: Enhancements (10 Days)**  
Sort/Filter Products **(4 days)				\-** Vithunan Ravichandran and Tisha Patel  
Login Feature **(2 Day)					\-** Omar Ahmed  
Overview of Inventory **(2 Days)			\-** Tony Ngo  
Inventory Purchase Orders **(2 Days )			\-** Abdullah Mobashar

**Iteration 3: Final Features (10 Days)**  
Inventory Purchasing **(2 Days)			\-** Abdullah Mobashar  
Adding to cart **(1 Day)				\-** Tisha Patel  
Customer Reviews **(3 Days)				\-** Vithunan Ravichandran and Tony Ngo  
Stock Notification **(2 Days)				\-** Omar Ahmed  
Sales Report **(2 Days) 				\-** Tisha Patel and Omar Ahmed  
Update Inventory **(2 days)				\-** Abdullah Mobashar and Tony Ngo

**Kanban Board Set Up:**  
[https://github.com/users/AbdullahM1234/projects/1/views/3?sliceBy%5Bvalue%5D=\_noValue](https://github.com/users/AbdullahM1234/projects/1/views/3?sliceBy%5Bvalue%5D=_noValue)

Screenshots of the iterations calendar will be added with the submission

**5\) Finalized Project Roadmap**

### **Summary of Major Milestones & Deliverables (6 Weeks)**

#### **Week 1-2: Core Functionality Development**

* **Deliverables:**  
  * Database Creation (Abdullah)  
  * Add/Edit/Remove Products Feature (Vithunan & Tisha)  
  * Search Function Implementation (Tony)  
  * Initial User Interface Setup (Omar)  
* **Milestone:** Fully functional database and basic inventory management capabilities.

#### **Week 3-4: Enhancements & User Experience**

* **Deliverables:**  
  * Sorting & Filtering Features (Vithunan & Tisha)  
  * Secure Login System (Omar)  
  * Inventory Overview Dashboard (Tony)  
  * Inventory Purchase Orders Feature (Abdullah)  
* **Milestone:** Improved usability with sorting, filtering, login, and inventory tracking.

#### **Week 5-6: Final Features & Testing**

* **Deliverables:**  
  * Inventory Purchasing Process (Abdullah)  
  * Cart Feature (Tisha)  
  * Customer Reviews (Vithunan & Tony)  
  * Stock Notifications (Omar)  
  * Sales Report Feature (Tisha & Omar)  
  * Weekly Inventory Updates (Abdullah & Tony)  
* **Milestone:** The full feature set is completed, tested, and ready for deployment.

### 

### 

### 

### **Key Risks & Potential Challenges**

* **Data Management Issues** → Ensure database integrity and proper backups.  
* **Security Concerns with Login System** → Implement encryption and authentication best practices.  
* **Feature Integration Delays** → Maintain clear communication and frequent code reviews.  
* **Testing & Debugging Bottlenecks** → Allocate dedicated time for quality assurance.

### 

### **Next Steps & Immediate Actions**

1. **Complete Core Functionality (Weeks 1-2)**  
   * Set up a dedicated GitHub repository for collaborative coding.  
   * Conduct initial database tests and verify CRUD operations.  
2. **Prepare for Enhancements (Weeks 3-4)**  
   * Hold team check-ins to review progress and address blockers.  
   * Begin integration of sorting, filtering, and login features.  
3. **Plan for Testing & Finalization (Weeks 5-6)**  
   * Conduct usability testing and bug fixes.  
   * Document system features and provide a user guide.  
   * Finalize the project for review and submission.

