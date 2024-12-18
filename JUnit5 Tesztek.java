@WebMvcTest(SubjectController.class)
class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @Test
    void testListSubjects() throws Exception {
        List<Subject> subjects = List.of(new Subject("user1", "MATH101", "Matematika 101"));
        Mockito.when(subjectService.getSubjectsByUserId("user1")).thenReturn(subjects);

        mockMvc.perform(get("/api/subjects/list").param("userId", "user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subjectName").value("Matematika 101"));
    }
}
