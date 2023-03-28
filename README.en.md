##Zeus User Manual

----------

Zeus Code Generator is an automated code generation tool designed to quickly generate basic CRUD code.



The back-end is implemented based on 'spring boot', 'freemarker', and 'mybatisplus'. The front-end uses' vue3', 'elementplus', and' electric 'technologies.



On this basis, some advanced functions are also provided to achieve flexible configuration and generate code with strong scalability. The client supports multiple opening. Multiple clients can be opened on the same computer. You can log in to the account of an administrator and an ordinary user, and the administrator can modify the template content. Normal user generated code.



-* * [ZEUS official website]（ https://engini.vip/zeus/docs )**

-* * [backend Gitee warehouse address]（ https://gitee.com/rederic/zeus-server.git ）**| [Front end Gitee warehouse address]（ https://gitee.com/rederic/zeus-view.git )

-* * [backend GitHub warehouse address]（ https://github.com/EricConstantine/zeus-server.git ）**| [Front end GitHub warehouse address]（ https://github.com/EricConstantine/zeus-view.git )



> [!TIP]

>* * Advantages**

> 1. Visualization of the project structure, file tree, and graphical template editing of files make the operation template more intuitive and concise.

> 2. Freemarker is used as the template engine, and all Freemarker syntax is supported in the template. The system provides a powerful built-in dictionary. Template operation is more flexible.

> 3. Zeus can be used as a MySQL or Oracle client to directly connect to intranet databases.

> 4. Script management. Replace Jenkins and quickly update packaged projects with lightweight. Web page viewing project logs and other functions.

> 5. Facilitate team development projects, unify code styles, and standardize code interfaces.



The client supports multiple opening. Multiple clients can be opened on the same computer. You can log in to the account of an administrator and an ordinary user, and the administrator can modify the template content. Normal user generated code.


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