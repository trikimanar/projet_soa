const express = require("express");
const cors = require("cors");
const connectDB = require("./database");
require("dotenv").config();

const studentRoutes = require("./routes/student.routes");

const app = express();
app.use(express.json());
app.use(cors());

// Connexion DB
connectDB();

// Routes
app.use("/students", studentRoutes);

app.listen(process.env.PORT, () => {
  console.log(`Student Service running on port ${process.env.PORT}`);
});
