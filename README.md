**Project Information**
-
This project is a multi-threaded Rock-Paper-Scissors game. Users can run a Rock Paper Scissors server that hosts up to two players that can choose their own usernames, have their match results stored/displayed, and can exit using the user interface.
Contributors:
Andy Wang - 100751519
Benjamin Tsoumagas - 100751395
Matthew Sharp - 100748071
Sam Shard - 100745640

Create client screen  
![alt-text](https://github.com/benjamin-tsoumagas/csci2020u-final/blob/main/README_images/create_client.png?raw=true)  
Login screen  
![alt-text](https://github.com/benjamin-tsoumagas/csci2020u-final/blob/main/README_images/login.png?raw=true)  
Welcome screen  
![alt-text](https://github.com/benjamin-tsoumagas/csci2020u-final/blob/main/README_images/welcome.png?raw=true)  
Choose action screen  
![alt-text](https://github.com/benjamin-tsoumagas/csci2020u-final/blob/main/README_images/choose_action.png?raw=true)  
Winner results screen  
![alt-text](https://github.com/benjamin-tsoumagas/csci2020u-final/blob/main/README_images/results_win.png?raw=true)  
Draw results screen  
![alt-text](https://github.com/benjamin-tsoumagas/csci2020u-final/blob/main/README_images/results_draw.png?raw=true)  

**How to run:**
-
1. Go to https://gluonhq.com/products/javafx/ and download the JavaFX x64 SDK for Windows/Mac/Linux depending on your operating system.
2. Follow installer instructions to complete the download.
3. Once complete, extract downloaded folder to a location you will remember.
4. Download IntelliJ here https://www.jetbrains.com/idea/.
5. Open the GitHub link here https://github.com/benjamin-tsoumagas/csci2020u-a1.
6. Click on the green "Code" button then click on the clipboard to copy the web URL.
7. Open a command prompt and type 'git clone https://github.com/benjamin-tsoumagas/csci2020u-a1.git (You will need to install Git if you haven't already https://git-scm.com/downloads)
8. Open this project using IntelliJ once the project is cloned and go to Run>Edit Configurations
9. Set up configurations for ClientTester and ServerTester, ClientTester needs this virtual machine argument: --module-path "***" --add-modules javafx.controls,javafx.fxml
   *** represents your path to your javafx sdk library
10. Before running ClientTester ensure ServerTester is running.
   (For any issues running the project with IntelliJ, please refer to this YouTube video by Bro Code https://youtu.be/Ope4icw6bVk?list=LL)


**Other Resources**
-
Icon from: https://th.bing.com/th/id/OIP.HbI6T3xeVO2XngKCsX9HRQAAAA?pid=ImgDet&rs=1 \
Rock, Paper and Scissors from: https://www.shutterstock.com/image-vector/rock-scissors-paper-hand-gesture-vector-689530327

