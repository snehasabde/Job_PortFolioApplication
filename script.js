const API_BASE_URL = 'http://localhost:8080/api'; // Ensure this matches your Spring Boot backend URL

async function fetchAndRenderData(endpoint, containerId, renderFunction) {
    try {
        const response = await fetch(`${API_BASE_URL}/${endpoint}`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        const container = document.getElementById(containerId);
        if (container) {
            container.innerHTML = ''; // Clear loading message
            if (data.length === 0) {
                container.innerHTML = `<p>No ${endpoint} available.</p>`;
            } else {
                renderFunction(data, container);
            }
        }
    } catch (error) {
        console.error(`Error fetching ${endpoint}:`, error);
        const container = document.getElementById(containerId);
        if (container) {
            container.innerHTML = `<p>Error loading ${endpoint}. Please try again.</p>`;
        }
    }
}

// --- Render Functions ---

function renderProfile(profileData, container) {
    if (profileData) {
        // Assuming profileData is a single object, not an array
        const profile = Array.isArray(profileData) ? profileData[0] : profileData; // Handle if it's somehow an array

        if (profile) {
            document.getElementById('hero-name').textContent = `Hi, I'm ${profile.name || 'Your Name'}`;
            document.getElementById('hero-title').textContent = profile.title || 'A passionate Full-Stack Developer';
            container.innerHTML = `<p>${profile.aboutMe || 'No "About Me" content available.'}</p>`;

            document.getElementById('contact-email').textContent = profile.email || 'N/A';
            
            const linkedinLink = document.getElementById('contact-linkedin');
            linkedinLink.href = profile.linkedinUrl || '#';
            linkedinLink.textContent = profile.linkedinUrl ? 'LinkedIn Profile' : 'N/A';
            linkedinLink.target = '_blank';

            const githubLink = document.getElementById('contact-github');
            githubLink.href = profile.githubUrl || '#';
            githubLink.textContent = profile.githubUrl ? 'GitHub Profile' : 'N/A';
            githubLink.target = '_blank';

            // Update header logo and footer name
            document.querySelector('.logo').textContent = profile.name || 'Your Name';
            document.getElementById('footer-name').textContent = profile.name || 'Your Name';

            // You can also dynamically set the profile image if stored in backend
            // const profilePic = document.querySelector('.profile-pic');
            // if (profile.profileImageUrl) {
            //     profilePic.src = profile.profileImageUrl;
            // }
        }
    }
}

function renderSkills(skills, container) {
    skills.forEach(skill => {
        const skillItem = document.createElement('div');
        skillItem.classList.add('skill-item');
        skillItem.textContent = skill.name;
        container.appendChild(skillItem);
    });
}

function renderProjects(projects, container) {
    projects.forEach(project => {
        const projectCard = document.createElement('div');
        projectCard.classList.add('project-card');

        if (project.imageUrl) {
            const img = document.createElement('img');
            img.src = project.imageUrl;
            img.alt = project.title;
            projectCard.appendChild(img);
        }

        const projectInfo = document.createElement('div');
        projectInfo.classList.add('project-info');

        const title = document.createElement('h3');
        title.textContent = project.title;
        projectInfo.appendChild(title);

        const description = document.createElement('p');
        description.textContent = project.description;
        projectInfo.appendChild(description);

        const technologies = document.createElement('p');
        technologies.classList.add('technologies');
        technologies.innerHTML = `<strong>Technologies:</strong> ${project.technologies}`;
        projectInfo.appendChild(technologies);

        const linksDiv = document.createElement('div');
        linksDiv.classList.add('project-links');

        if (project.liveDemoUrl) {
            const liveDemoLink = document.createElement('a');
            liveDemoLink.href = project.liveDemoUrl;
            liveDemoLink.textContent = 'Live Demo';
            liveDemoLink.classList.add('live-demo');
            liveDemoLink.target = '_blank';
            liveDemoLink.innerHTML += ' <i class="fas fa-external-link-alt"></i>'; // Icon
            linksDiv.appendChild(liveDemoLink);
        }

        if (project.sourceCodeUrl) {
            const sourceCodeLink = document.createElement('a');
            sourceCodeLink.href = project.sourceCodeUrl;
            sourceCodeLink.textContent = 'Source Code';
            sourceCodeLink.classList.add('source-code');
            sourceCodeLink.target = '_blank';
            sourceCodeLink.innerHTML += ' <i class="fab fa-github"></i>'; // Icon
            linksDiv.appendChild(sourceCodeLink);
        }

        projectInfo.appendChild(linksDiv);
        projectCard.appendChild(projectInfo);
        container.appendChild(projectCard);
    });
}

function renderExperience(experiences, container) {
    experiences.forEach(exp => {
        const expItem = document.createElement('div');
        expItem.classList.add('experience-item');
        expItem.innerHTML = `
            <h4>${exp.company || 'N/A'}</h4>
            <p class="job-title">${exp.jobTitle || 'N/A'}</p>
            <p class="date-range">${exp.startDate || 'N/A'} - ${exp.endDate || 'Present'}</p>
            <p>${exp.description || ''}</p>
        `;
        container.appendChild(expItem);
    });
}

function renderEducation(educationList, container) {
    educationList.forEach(edu => {
        const eduItem = document.createElement('div');
        eduItem.classList.add('education-item');
        eduItem.innerHTML = `
            <h4>${edu.institution || 'N/A'}</h4>
            <p class="degree">${edu.degree || 'N/A'}</p>
            <p class="graduation-date">Graduation: ${edu.graduationDate || 'N/A'}</p>
            <p>${edu.description || ''}</p>
        `;
        container.appendChild(eduItem);
    });
}


// --- On Document Load ---
document.addEventListener('DOMContentLoaded', () => {
    // Fetch and render all data
    fetchAndRenderData('profile', 'about-content', renderProfile); // Profile updates multiple sections
    fetchAndRenderData('skills', 'skills-list', renderSkills);
    fetchAndRenderData('projects', 'projects-grid', renderProjects);
    fetchAndRenderData('experience', 'experience-list', renderExperience);
    fetchAndRenderData('education', 'education-list', renderEducation);
});