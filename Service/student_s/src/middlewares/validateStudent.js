const validateStudent = (req, res, next) => {
  const { firstName, lastName, email, department } = req.body;

  if (!firstName || !lastName || !email || !department) {
    return res.status(400).json({ error: "All fields are required" });
  }

  // Optionnel : vérification format email
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    return res.status(400).json({ error: "Invalid email format" });
  }

  next();
};

module.exports = validateStudent;