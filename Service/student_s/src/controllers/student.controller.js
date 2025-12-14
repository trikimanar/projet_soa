const Student = require("../models/student.model");

// ➕ Ajouter étudiant
exports.addStudent = async (req, res) => {
  try {
    const student = await Student.create(req.body);
    res.status(201).json(student);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// 📄 Afficher tous les étudiants
exports.getAllStudents = async (req, res) => {
  try {
    const students = await Student.find();
    res.json(students);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// 🗑️ Supprimer étudiant
exports.deleteStudent = async (req, res) => {
  try {
    const deleted = await Student.findByIdAndDelete(req.params.id);
    res.json({ message: "Deleted", deleted });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
// controllers/student.controller.js
exports.updateStudent = async (req, res) => {
    try {
        const student = await Student.findByIdAndUpdate(req.params.id, req.body, { new: true });
        res.status(200).json(student);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
};


