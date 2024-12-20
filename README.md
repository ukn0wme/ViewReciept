# Recipe Search and Filter Project

This project utilizes the [TheMealDB API](https://www.themealdb.com/api.php) to enable users to:

1. **Search recipes by name**
2. **View detailed information about selected recipes**
3. **Filter recipes by region**
4. **Save favorite recipes locally**

## Why This Project?

Whether you’re a home cook, a food blogger, or just someone looking for culinary inspiration, this project provides a straightforward interface to discover new recipes. It’s designed to be flexible and can be integrated into any frontend framework or standalone JavaScript application.

## Features

- **Search Recipes:** Enter a keyword (e.g., "Arrabiata") to find recipes containing that term.
- **Recipe Details:** View detailed instructions, ingredients, and measurements for a specific recipe.
- **Filter by Region:** Narrow down search results by selecting a specific regional cuisine (e.g., "Canadian").
- **Save Favorites:** Mark and store your favorite recipes locally (e.g., using `localStorage`).

## API Endpoints

The project leverages the following endpoints from TheMealDB:

1. **Search Recipes by Name**  
   - **Endpoint:** `https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata`  
   - **Usage:** Replace `Arrabiata` with any keyword to retrieve matching recipes.

2. **Get Recipe Details by ID**  
   - **Endpoint:** `https://www.themealdb.com/api/json/v1/1/lookup.php?i=52772`  
   - **Usage:** Replace `52772` with the desired recipe’s ID to fetch detailed info.

3. **Filter Recipes by Region (Area)**  
   - **Endpoint:** `https://www.themealdb.com/api/json/v1/1/filter.php?a=Canadian`  
   - **Usage:** Replace `Canadian` with another region name (e.g., `Italian`, `Chinese`, `Mexican`) to filter recipes from that cuisine.

## Getting Started

### Prerequisites

- A code editor - Android studio
- A modern browser or a tool like `Postman` for testing API responses.

### Installation

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/your-recipe-project.git](https://github.com/ukn0wme/ViewReciept/)
