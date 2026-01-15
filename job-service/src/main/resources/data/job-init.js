//create DB
db = db.getSiblingDB('job');

//create user
db.createUser({
    user: 'jobUser',
    pwd: 'jobPswd',
    roles: [{ role: 'readWrite', db: 'job' }],
});

//create collection
db.createCollection('jobs');

//create docs
db.jobs.insertMany([
    {
        description: 'Software Engineer',
        company: 'Tech Corp',
        skills: ['JavaScript', 'Node.js', 'MongoDB','AWS'],
        salary: 90000,
        remote: true
    },
    {
        description: 'Data Scientist',
        company: 'Data Inc',
        skills: ['Python', 'Machine Learning', 'Statistics'],
        salary: 110000,
        remote: false
    },
    {
        description: 'Frontend Developer',
        company: 'Web Solutions',
        skills: ['HTML', 'CSS', 'React'],
        salary: 80000,
        remote: true
    },
    {
        description: 'DevOps Engineer',
        company: 'Cloud Services',
        skills: ['AWS', 'Docker', 'Kubernetes'],
        salary: 95000,
        remote: false
    },
    {
        description: 'Project Manager',
        company: 'Business Corp',
        skills: ['Leadership', 'Communication', 'Agile'],
        salary: 85000,
        remote: true
    }
]);