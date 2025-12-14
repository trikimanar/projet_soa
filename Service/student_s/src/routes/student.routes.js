const express = require("express");
const router = express.Router();

const { addStudent, getAllStudents, deleteStudent, updateStudent } = require("../controllers/student.controller");
const validateStudent = require("../middlewares/validateStudent");

// ➕ POST student
router.post("/", validateStudent, addStudent);

// 📄 GET all students
router.get("/", getAllStudents);

// 🗑️ DELETE student by id
router.delete("/:id", deleteStudent);
//
router.put("/:id", validateStudent, updateStudent);
module.exports = router;
