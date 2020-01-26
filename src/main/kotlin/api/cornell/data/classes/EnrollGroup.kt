package api.cornell.data.classes

/**
 * @param classSections a list of class sections.
 * @param unitsMinimum minimum units.
 * @param unitsMaximum maximum units.
 * @param componentsRequired a list of required components.
 * @param componentsOptional a list of optional components.
 * @param gradingBasis basis of grading.
 */
data class EnrollGroup(
        val classSections: List<ClassSection>,
        val unitsMinimum: Double,
        val unitsMaximum: Double,
        val componentsRequired: List<ClassComponent>,
        val componentsOptional: List<ClassComponent>,
        val gradingBasis: GradingBasis
)
