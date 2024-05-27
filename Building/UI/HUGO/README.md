## HUGO
The world’s fastest framework for building websites
Hugo is one of the most popular open-source static site generators. With its amazing speed and flexibility, Hugo makes building websites fun again.



## Getting Started
Step1: Install Hugo and verify version 
```
winget install Hugo.Hugo.Extended
hugo version
```

Step2: Create a Website with YML configuration 
```
hugo new site my-hugo-blog -f yml

```

### Check the Folder Structure 
```mermaid
graph TD
    A[my-hugo-blog]
    A --> B[archetypes]
    B --> B1[default.md]
    A --> C[assets]
    A --> D[config.toml]
    A --> E[content]
    E --> E1[_index.md]
    E --> E2[about]
    E2 --> E2_1[_index.md]
    E --> E3[posts]
    E3 --> E3_1[first-post.md]
    E3 --> E3_2[second-post.md]
    A --> F[data]
    A --> G[layouts]
    G --> G1[_default]
    G1 --> G1_1[baseof.html]
    G1 --> G1_2[list.html]
    G1 --> G1_3[single.html]
    G --> G2[partials]
    G2 --> G2_1[footer.html]
    G2 --> G2_2[header.html]
    G --> G3[index.html]
    A --> H[static]
    H --> H1[css]
    H1 --> H1_1[style.css]
    H --> H2[images]
    H2 --> H2_1[logo.png]
    H --> H3[js]
    H3 --> H3_1[main.js]
    A --> I[themes]
    I --> I1[my-theme]
    I1 --> I1_1[archetypes]
    I1 --> I1_2[assets]
    I1 --> I1_3[layouts]
    I1 --> I1_4[static]
    I1 --> I1_5[theme.toml]

```





### Link:
[Hugo](https://gohugo.io/)
