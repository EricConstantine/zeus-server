#ZEUS code generator background



## 1. Technical framework



Zeus code generator background is developed using spring boot, mybatisplus, and freemarker technologies



Zeus code generator foreground is developed using vue3, typescript, and elementplus technologies



## 2. How to get started



1. Create a new database code



2. Import code.sql



3. Clone background project code to local



```

git clone https://gitee.com/rederic/zeus-server.git

```



4. Launch the spring boot project



5. Clone foreground code to local



```

git clone https://gitee.com/rederic/zeus-view.git

```



6. Start front-end project



```

npm install

npm run dev

```



## 3. Rapid development



You can quickly generate front-end and back-end code using a code generator back-end template



## 4. Project Packaging Deployment



Packaging and deployment of foreground projects



```

npm run build:prod

```



Find dist_ Install the zeus Setup 2.7.8.exe file in the electric folder



Background project packaging and deployment



```

mvn package -DskipTests

```